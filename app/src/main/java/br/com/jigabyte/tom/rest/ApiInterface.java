package br.com.jigabyte.tom.rest;

import br.com.jigabyte.tom.model.Usuario;
import br.com.jigabyte.tom.model.response.UsuarioResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {
    /*
    @Headers({
                "Accept: application/vnd.github.v3.full+json"
    })

    @GET("movie/top_rated")
    Call<MoviesResponse> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("movie/{id}")
    Call<MoviesResponse> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);
    */

    @Headers({ "Accept: application/json" })
    @GET("usuarios/all")
    Call<UsuarioResponse> getBuscarAllUsuarios();

    @Headers({ "Accept: application/json" })
    @POST("usuarios")
    Call<UsuarioResponse> postSaveUsuario(@Body Usuario usuario);

    @Headers({ "Accept: application/json" })
    @POST("usuarios/login")
    Call<UsuarioResponse> postLogin(@Body Usuario usuario);

    @Headers({ "Accept: application/json" })
    @GET("usuarios/{id}")
    Call<UsuarioResponse> getBuscarUsuario(@Path("id") String id_usuario, @Header("code") String code); // ASNJf9NwBA//Irr0TZ9Yka17pUSQ/OLMP6kobb75trArnnAjTNWOZn/mVLyn26n3



}
