package ca.wchang.openMoives.service;

import ca.wchang.openMoives.dao.GenreMapper;
import ca.wchang.openMoives.dao.Movie_infoMapper;
import ca.wchang.openMoives.model.Genre;
import ca.wchang.openMoives.model.GenreDetail;
import ca.wchang.openMoives.model.Movie_info;
import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class MovieService {

    @Value("${spring.datasource.url}")
    private String dbURL;
    @Value("${spring.datasource.username}")
    private String dbUserName;
    @Value("${spring.datasource.password}")
    private String dbPassword;

    @Value("${tmdb}")
    private String tmdbUrl;
    @Value("${ver}")
    private String ver;
    @Value("${path.discover}")
    private String discover;
    @Value("${path.genre}")
    private String genre;
    @Value("${api.key}")
    private String key;


    @Autowired
    private Movie_infoMapper movie_infoMapper;

    @Autowired
    private GenreMapper genreMapper;

    @Autowired
    private RestTemplate restTemplate;

    HttpHeaders headers = null;
    HttpEntity<String> entity = null;
    MovieService() {
        headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent",
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        entity = new HttpEntity<String>("parameters", headers);
    }

    public Movie_info getList(String title)
    {
       return movie_infoMapper.getByTitle(title);
    }

    public ArrayList<Movie_info> getAll() {
        return movie_infoMapper.getAll("pop");
    }

    void updateBudgetAndRevenueFrmServer(Integer movie_id) {
        try {
            String query = tmdbUrl + "/" + ver + "/" +
                    "/" + "movie" + "/" + movie_id + "?api_key=" + key + "&language=en-US";

            ResponseEntity<String> response = restTemplate.exchange(query, HttpMethod.GET, entity, String.class);
            JsonValue movieDetail = Json.parse(response.getBody()).asObject();
            Movie_info info = new Movie_info();
            info.setBudget(movieDetail.asObject().get("budget").asInt());
            info.setRevenue(movieDetail.asObject().get("revenue").asInt());
            info.setMovie_id(movie_id);
            movie_infoMapper.updateBudgetAndRevenue(info);
        }
        catch ( HttpStatusCodeException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    HashMap<Integer,String> getGenres() {
        try {
            String query = tmdbUrl + "/" + ver + "/" + genre +
                    "/" + "movie/list" + "?api_key=" + key + "&language=en-US";
            ResponseEntity<String> response = restTemplate.exchange(query, HttpMethod.GET, entity, String.class);
            JsonArray genreList = Json.parse(response.getBody()).asObject().get("genres").asArray();
            HashMap<Integer,String> genreMap = new HashMap<>();
            for(JsonValue genre: genreList) {
                genreMap.put(genre.asObject().get("id").asInt(),genre.asObject().get("name").asString());
            }
            return genreMap;
        } catch ( HttpStatusCodeException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String updateDBfromServer(Integer  year) {
        try{
            String query = tmdbUrl + "/" + ver + "/" + discover +
                    "/" + "movie" + "?api_key=" + key +
                    "&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1&primary_release_year=" + year;
            ResponseEntity<String> response = restTemplate.exchange(query, HttpMethod.GET, entity, String.class);
            JsonArray movieJsonArr = Json.parse(response.getBody()).asObject().get("results").asArray();
            movie_infoMapper.deleteAll();
            genreMapper.deleteAll();
            Movie_info info = new Movie_info();
            Genre genre = new Genre();
            HashMap<Integer,String>  genreMap = getGenres();
            for(JsonValue movie : movieJsonArr) {
                info.setTitle(movie.asObject().get("title").asString());
                info.setOverview(movie.asObject().get("overview").asString());
                info.setPop(movie.asObject().get("popularity").asDouble());
                info.setVote_avg(movie.asObject().get("vote_average").asFloat());
                info.setPoster_path(movie.asObject().get("poster_path").asString());
                info.setVote_cnt(movie.asObject().get("vote_count").asInt());
                info.setMovie_id(movie.asObject().get("id").asInt());
                JsonArray genreArr = movie.asObject().get("genre_ids").asArray();
                for(JsonValue genreVal : genreArr) {
                    genre.setGenre_id(info.getMovie_id());
                    genre.setCategory_id(genreVal.asInt());
                    genre.setCategory_name(genreMap.get(genreVal.asInt()));
                    genreMapper.insert(genre);
                }
                info.setRelease_date(movie.asObject().get("release_date").asString());
                info.setGenre_id(info.getMovie_id());
                info.setBudget(0);

                movie_infoMapper.insert(info);
            }
            ArrayList<Integer> movieIdList = movie_infoMapper.getAllMovieIdList();
            movieIdList.parallelStream().forEach(id -> updateBudgetAndRevenueFrmServer(id));

            return "Updated movies in total: " + movieIdList.size();
        }
        catch (Exception e) {
            e.printStackTrace();
            return "Error";
        }
    }

    public HashMap<Integer, List<Genre>>  getGenreList() {
        System.out.println(tmdbUrl);
        System.out.println(dbUserName);
        System.out.println(dbPassword);
        List<GenreDetail>  genreList = genreMapper.getGenreList();
        HashMap<Integer, List<Genre>> genreMap = new HashMap<>();
        List<Genre> tempGenreList;

        for(GenreDetail genreDetail : genreList) {
            Genre genre = new Genre();
            genre.setCategory_name(genreDetail.getCategory_name());
            genre.setGenre_id(genreDetail.getCategory_id());

           if(genreMap.containsKey(genreDetail.getMovie_id())) {
               tempGenreList = genreMap.get(genreDetail.getMovie_id());
               tempGenreList.add(genre);
               genreMap.replace(genreDetail.getMovie_id(), tempGenreList);
           }
           else {
               tempGenreList = new ArrayList<>();
               tempGenreList.add(genre);
               genreMap.put(genreDetail.getMovie_id(), tempGenreList);
           }
        }
        return genreMap;

    }
}
