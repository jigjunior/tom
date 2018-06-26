package br.com.jigabyte.tom.rest;

import java.util.List;

import br.com.jigabyte.tom.model.Poltrona;
import br.com.jigabyte.tom.model.Usuario;
import br.com.jigabyte.tom.model.UsuarioLogin;
import br.com.jigabyte.tom.model.Voo;
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


    /**---------------------------------- USUARIOS -------------------------------------*/
    @Headers({ "Accept: application/json" })
    @GET("usuarios/all")
    Call<List<Usuario>> getAllUsuarios();

    @Headers({ "Accept: application/json" })
    @POST("usuarios")
    Call<Usuario> postSaveUsuario(@Body Usuario usuario);

    @Headers({ "Accept: application/json" })
    @POST("usuarios/login")
    Call<Usuario> postLogin(@Body UsuarioLogin usuario);

    @Headers({ "Accept: application/json" })
    @GET("usuarios/{id}")
    //Call<Usuario> getBuscarUsuario(@Path("id") String id_usuario, @Header("code") String code); //  code = ASNJf9NwBA//Irr0TZ9Yka17pUSQ/OLMP6kobb75trArnnAjTNWOZn/mVLyn26n3
    Call<Usuario> getBuscarUsuario(@Path("id") long id_usuario,
                                   @Header("code") String code);


    /**----------------------------------   VOOS   -------------------------------------*/
    @Headers({ "Accept: application/json" })
    @GET("voo")
    Call<List<Voo>> getAllVoos(@Header("code") String code);

    @Headers({ "Accept: application/json" })
    @GET("voo/{id}")
    Call<Voo> getVoo(@Path("id") long id_voo,
                     @Header("code") String code);

    @Headers({ "Accept: application/json" })
    @GET("voo/{id}")
    Call<List<Poltrona>> getPoltronas(@Path("id") long id_voo,
                                      @Header("code") String code);

    @Headers({ "Accept: application/json" })
    @GET("voo/{id_voo}/poltronas/{id_poltrona}")
    Call<Poltrona> getPoltronas(@Path("id_voo") long id_voo,
                                @Path("id_poltrona") long id_poltrona,
                                @Header("code") String code);

    @Headers({ "Accept: application/json" })
    @POST("/voo/{id_voo}/poltronas/{id_poltrona}")
    Call<Usuario> postSavePoltrona(@Path("id_voo") long id_voo,
                                   @Path("id_poltrona") long id_poltrona,
                                   @Header("code") String code);

    /**----------------------------------  CARTAO  -------------------------------------*/
    @Headers({ "Accept: application/json" })
    @GET("cartao/{cartao}/{mes}/{ano}/{tarja}/{valor}")
    Call<Poltrona> getPoltronas(@Path("cartao") String cartao,
                                @Path("mes") int mes,
                                @Path("ano") int ano,
                                @Path("tarja") String tarja,
                                @Path("valor") double valor);

}
