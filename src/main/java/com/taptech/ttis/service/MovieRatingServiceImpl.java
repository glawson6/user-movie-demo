package com.taptech.ttis.service;

import com.taptech.ttis.dto.MovieDTO;
import com.taptech.ttis.dto.UserMovieDTO;
import com.taptech.ttis.entity.Movie;
import com.taptech.ttis.entity.User;
import com.taptech.ttis.repository.MovieRepository;
import com.taptech.ttis.repository.UserRepository;
import com.taptech.ttis.util.MovieDTORowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.sql.DataSource;
import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by tap on 7/30/16.
 */
@Service("movieRatingService")
public class MovieRatingServiceImpl implements MovieRatingService {

    private static final Logger logger = LoggerFactory.getLogger(MovieRatingServiceImpl.class);

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Autowired
    EntityManager entityManager;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    MovieDTORowMapper movieDTORowMapper;

    @Override
    public Collection<MovieDTO> findUserMovies(String userId) {
        return null;
    }

    @Override
    public Collection<MovieDTO> findTopMovies(String genre, String userId) {

        /*  public <T> List<T> query(String sql, Object[] args, RowMapper<T> rowMapper) throws DataAccessException {
        return (List)this.query((String)sql, (Object[])args, (ResultSetExtractor)(new RowMapperResultSetExtractor(rowMapper)));
    } */
        List<MovieDTO> resultList = (List<MovieDTO>)this.jdbcTemplate.query(AVERAGE_GENRE_OTHER_USERS,
                new Object[]{genre, userId, userId}, movieDTORowMapper);
        List<MovieDTO> movieDTOs = resultList.stream().limit(5).collect(toList());
        return movieDTOs;
    }

    @Override
    public Double averageRating(String movieId) {
        Movie movie = movieRepository.findOne(movieId);
        Query query = entityManager.createNamedQuery("MovieRating.findAverageRating");
        query.setParameter("movieid",movie);
        Double actual = (Double) query.getSingleResult();
        return actual;
    }

    private static final String WATCHED_MOVIES = "SELECT mv.movieid, mv.name, mr.rating, g.name " +
            "FROM MovieRating mr JOIN mr.movieid mv " +
            "JOIN mv.genreid g " +
            "WHERE mr.userid = :userid " +
            "GROUP BY mv.movieid";

    private static final String AVERAGE_GENRE_OTHER_USERS = "select mv.movieid, mv.name, avg(mr.rating) as avg, g.name as genre from movie_rating mr " +
            "join movie mv on mv.movieid = mr.movieid " +
            "join (select gen.genreid, gen.name from genre gen where gen.name = ?) g on g.genreid = mv.genreid " +
            "join user usr1 on usr1.userid = ? " +
            "join (select ur.age, ur.name, ur.userid from user ur where ur.userid != ? ) usr2 on usr2.userid = mr.userid " +
            "where usr2.age >= (usr1.age-5) and usr2.age <= (usr1.age+5) " +
            "GROUP by mr.movieid " +
            "ORDER BY avg DESC";

    @Override
    public UserMovieDTO watchedMovies(String userId) {
        UserMovieDTO userMovieDTO = new UserMovieDTO();
        User userid = userRepository.findOne(userId);
        TypedQuery<Object[]> query = getEntityManager().createQuery(WATCHED_MOVIES, Object[].class).setParameter("userid", userid);

        List<Object[]> resultList = query.getResultList();
        List<MovieDTO> movieDTOs = resultList.stream().map((result)-> {
            return movieDTORowMapper.convertToMovieDTO(result);
        } ).collect(toList());
        userMovieDTO.setMoviesWatched(movieDTOs);
        userMovieDTO.setTotalCount(movieDTOs.size());
        userMovieDTO.setUserId(userId);
        return userMovieDTO;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
