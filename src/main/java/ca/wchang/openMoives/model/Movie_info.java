package ca.wchang.openMoives.model;

public class Movie_info {

    private Integer id;
    private String title;
    private Integer vote_cnt;
    private Float vote_avg;
    private Double pop;
    private Integer genre_id;
    private String poster_path;
    private Integer budget;
    private Integer revenue;
    private String overview;
    private String release_date;
    private Integer movie_id;


    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRevenue() {
        return revenue;
    }

    public void setRevenue(Integer revenue) {
        this.revenue = revenue;
    }

    public Integer getBudget() {
        return budget;
    }

    public void setBudget(Integer budget) {
        this.budget = budget;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public Integer getGenre_id() {
        return genre_id;
    }

    public void setGenre_id(Integer genre_id) {
        this.genre_id = genre_id;
    }

    public Double getPop() {
        return pop;
    }

    public void setPop(Double pop) {
        this.pop = pop;
    }

    public Integer getVote_cnt() {
        return vote_cnt;
    }

    public void setVote_cnt(Integer vote_cnt) {
        this.vote_cnt = vote_cnt;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public Float getVote_avg() {
        return vote_avg;
    }

    public void setVote_avg(Float vote_avg) {
        this.vote_avg = vote_avg;
    }

    public Integer getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(Integer movie_id) {
        this.movie_id = movie_id;
    }
}
