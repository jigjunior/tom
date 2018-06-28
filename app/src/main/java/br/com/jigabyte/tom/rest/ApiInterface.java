package br.com.jigabyte.tom.rest;

import java.util.List;

import br.com.jigabyte.tom.model.Poltrona;
import br.com.jigabyte.tom.model.PoltronaResponse;
import br.com.jigabyte.tom.model.Usuario;
import br.com.jigabyte.tom.model.UsuarioCadastro;
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
//    [
//  {
//    "id": 1,
//    "nome": "Zezin",
//    "email": "ze@ze",
//    "token": "20 passagens compradas"
//  },
//  {
//    "id": 2,
//    "nome": "Pedrin",
//    "email": "ped@ped",
//    "token": "8 passagens compradas"
//  },
//  {
//    "id": 3,
//    "nome": "Gustin",
//    "email": "gugu@gu",
//    "token": "8 passagens compradas"
//  }

    @Headers({ "Accept: application/json" })
    @POST("usuarios")
    Call<Usuario> postSaveUsuario(@Body UsuarioCadastro usuario);
//curl -X POST --header 'Content-Type: application/json' --header 'Accept: application/json' -d '{ \
//    "email": "aaa", \
//    "id": 0, \
//    "login": "aaa", \
//    "nome": "aaa", \
//    "senha": "aaa" \
//}' 'https://service.davesmartins.com.br/api/usuarios'

//    {
//        "id": 135,
//            "nome": "aaa",
//            "email": "aaa",
//            "login": "aaa",
//            "senha": "aaa"
//    }

    @Headers({ "Accept: application/json" })
    @POST("usuarios/login")
    Call<Usuario> postLogin(@Body UsuarioLogin usuario);
//curl -X POST --header 'Content-Type: application/json' --header 'Accept: application/json' -d '{ \
//        "login": "aaa", \
//        "senha": "aaa" \
//}' 'https://service.davesmartins.com.br/api/usuarios/login'
//
//        {
//        "id": 135,
//        "nome": "aaa",
//        "email": "aaa",
//        "token": "0blqphjjHJieIZ9kF5rAxA5YE+Bd83PrAueFFHTg4oOuBp55WXLkTnduAQ42XYv4"
//        }

    @Headers({ "Accept: application/json" })
    @GET("usuarios/{id}")
    //Call<Usuario> getBuscarUsuario(@Path("id") String id_usuario, @Header("code") String code); //  code = ASNJf9NwBA//Irr0TZ9Yka17pUSQ/OLMP6kobb75trArnnAjTNWOZn/mVLyn26n3
    Call<Usuario> getBuscarUsuario(@Path("id") long id_usuario,
                                   @Header("code") String code);
//    curl -X GET --header 'Accept: application/json' --header 'code: 0blqphjjHJieIZ9kF5rAxA5YE+Bd83PrAueFFHTg4oOuBp55WXLkTnduAQ42XYv4' 'https://service.davesmartins.com.br/api/usuarios/135'
//
//{
//    "id": 135,
//    "nome": "aaa",
//    "email": "aaa",
//    "token": "",
//    "passagens": [
//        {
//            "assento": "39",
//            "ocupado": true,
//            "origem": "Curitiba",
//            "destino": "Confins",
//            "dataVoo": "28/08/2018 02:00:00",
//            "valorPassagem": 580,
//            "aviao": "Air Bus"
//        },
//        {
//            "assento": "29",
//            "ocupado": true,
//            "origem": "Curitiba",
//            "destino": "Confins",
//            "dataVoo": "28/08/2018 02:00:00",
//            "valorPassagem": 580,
//            "aviao": "Air Bus"
//        }
//    ]
//}



    /**----------------------------------   VOOS   -------------------------------------*/
    @Headers({ "Accept: application/json" })
    @GET("voo")
    Call<List<Voo>> getAllVoos(@Header("code") String code);
//[
//    {
//        "id": 1,
//        "origem": {
//            "id": 7,
//            "aeroporto": "Aeroporto do Bacacheri ",
//            "cidade": "Curitiba"
//         },
//        "destino": {
//            "id": 1,
//            "aeroporto": "Aeroporto Internacional de Belo Horizonte-Confins",
//            "cidade": "Confins"
//        },
//        "dataVoo": "28/08/2018 02:00:00",
//        "valorPassagem": 580,
//        "aviao": {
//            "id": 2,
//            "prefixo": "PT-1111",
//            "modelo": "Air Bus",
//            "capacidade": 90
//        }
//    },
//    {
//        "id": 2,
//        "origem": {
//            "id": 19,
//            "aeroporto": "Aeroporto Internacional de Campo Grande - Antônio João",
//            "cidade": "Campo Grande"
//            },
//        "destino": {
//            "id": 5,
//            "aeroporto": "Aeroporto Internacional de São Paulo - Guarulhos",
//            "cidade": "Guarulhos"
//            },
//        "dataVoo": "01/08/2018 05:00:00",
//        "valorPassagem": 470,
//        "aviao": {
//            "id": 1,
//            "prefixo": "PT-23456",
//            "modelo": "Boing 747",
//            "capacidade": 125
//        }
//    }
//]

    @Headers({ "Accept: application/json" })
    @GET("voo/{id}")
    Call<Voo> getVoo(@Path("id") long id_voo,
                     @Header("code") String code);
//    {
//        "id": 1,
//            "origem": {
//        "id": 7,
//                "aeroporto": "Aeroporto do Bacacheri ",
//                "cidade": "Curitiba"
//    },
//        "destino": {
//        "id": 1,
//                "aeroporto": "Aeroporto Internacional de Belo Horizonte-Confins",
//                "cidade": "Confins"
//    },
//        "dataVoo": "28/08/2018 02:00:00",
//            "valorPassagem": 580,
//            "aviao": {
//        "id": 2,
//                "prefixo": "PT-1111",
//                "modelo": "Air Bus",
//                "capacidade": 90
//    }
//    }

    @Headers({ "Accept: application/json" })
    @GET("voo/{id}")
    Call<List<PoltronaResponse>> getPoltronas(@Path("id") long id_voo,
                                              @Header("code") String code);


    // id = 1
//[
//    {
//        "assento": "1",
//            "ocupado": true,
//            "origem": "Curitiba",
//            "destino": "Confins",
//            "dataVoo": "28/08/2018 02:00:00",
//            "valorPassagem": 0,
//            "aviao": "Air Bus"
//    },
//    {
//        "assento": "2",
//            "ocupado": true,
//            "origem": "Curitiba",
//            "destino": "Confins",
//            "dataVoo": "28/08/2018 02:00:00",
//            "valorPassagem": 0,
//            "aviao": "Air Bus"
//    },
//    {
//        "assento": "3",
//            "ocupado": true,
//            "origem": "Curitiba",
//            "destino": "Confins",
//            "dataVoo": "28/08/2018 02:00:00",
//            "valorPassagem": 0,
//            "aviao": "Air Bus"
//    },
//    {
//        "assento": "4",
//            "ocupado": true,
//            "origem": "Curitiba",
//            "destino": "Confins",
//            "dataVoo": "28/08/2018 02:00:00",
//            "valorPassagem": 0,
//            "aviao": "Air Bus"
//    },
//    {
//        "assento": "5",
//            "ocupado": true,
//            "origem": "Curitiba",
//            "destino": "Confins",
//            "dataVoo": "28/08/2018 02:00:00",
//            "valorPassagem": 0,
//            "aviao": "Air Bus"
//    },
//    {
//        "assento": "6",
//            "ocupado": true,
//            "origem": "Curitiba",
//            "destino": "Confins",
//            "dataVoo": "28/08/2018 02:00:00",
//            "valorPassagem": 0,
//            "aviao": "Air Bus"
//    },
//    {
//        "assento": "7",
//            "ocupado": true,
//            "origem": "Curitiba",
//            "destino": "Confins",
//            "dataVoo": "28/08/2018 02:00:00",
//            "valorPassagem": 0,
//            "aviao": "Air Bus"
//    },
//    {
//        "assento": "8",
//            "ocupado": true,
//            "origem": "Curitiba",
//            "destino": "Confins",
//            "dataVoo": "28/08/2018 02:00:00",
//            "valorPassagem": 0,
//            "aviao": "Air Bus"
//    },
//    {
//        "assento": "9",
//            "ocupado": true,
//            "origem": "Curitiba",
//            "destino": "Confins",
//            "dataVoo": "28/08/2018 02:00:00",
//            "valorPassagem": 0,
//            "aviao": "Air Bus"
//    },
//    {
//        "assento": "10",
//            "ocupado": true,
//            "origem": "Curitiba",
//            "destino": "Confins",
//            "dataVoo": "28/08/2018 02:00:00",
//            "valorPassagem": 0,
//            "aviao": "Air Bus"
//    },
//    {
//        "assento": "11",
//            "ocupado": true,
//            "origem": "Curitiba",
//            "destino": "Confins",
//            "dataVoo": "28/08/2018 02:00:00",
//            "valorPassagem": 0,
//            "aviao": "Air Bus"
//    },
//    {
//        "assento": "12",
//            "ocupado": true,
//            "origem": "Curitiba",
//            "destino": "Confins",
//            "dataVoo": "28/08/2018 02:00:00",
//            "valorPassagem": 0,
//            "aviao": "Air Bus"
//    },
//    {
//        "assento": "13",
//            "ocupado": true,
//            "origem": "Curitiba",
//            "destino": "Confins",
//            "dataVoo": "28/08/2018 02:00:00",
//            "valorPassagem": 0,
//            "aviao": "Air Bus"
//    },
//    {
//        "assento": "14",
//            "ocupado": true,
//            "origem": "Curitiba",
//            "destino": "Confins",
//            "dataVoo": "28/08/2018 02:00:00",
//            "valorPassagem": 0,
//            "aviao": "Air Bus"
//    },
//    {
//        "assento": "15",
//            "ocupado": true,
//            "origem": "Curitiba",
//            "destino": "Confins",
//            "dataVoo": "28/08/2018 02:00:00",
//            "valorPassagem": 0,
//            "aviao": "Air Bus"
//    },
//    {
//        "assento": "16",
//            "ocupado": true,
//            "origem": "Curitiba",
//            "destino": "Confins",
//            "dataVoo": "28/08/2018 02:00:00",
//            "valorPassagem": 0,
//            "aviao": "Air Bus"
//    },
//    {
//        "assento": "17",
//            "ocupado": true,
//            "origem": "Curitiba",
//            "destino": "Confins",
//            "dataVoo": "28/08/2018 02:00:00",
//            "valorPassagem": 0,
//            "aviao": "Air Bus"
//    },
//    {
//        "assento": "18",
//            "ocupado": true,
//            "origem": "Curitiba",
//            "destino": "Confins",
//            "dataVoo": "28/08/2018 02:00:00",
//            "valorPassagem": 0,
//            "aviao": "Air Bus"
//    },
//    {
//        "assento": "19",
//            "ocupado": true,
//            "origem": "Curitiba",
//            "destino": "Confins",
//            "dataVoo": "28/08/2018 02:00:00",
//            "valorPassagem": 0,
//            "aviao": "Air Bus"
//    },
//    {
//        "assento": "20",
//            "ocupado": true,
//            "origem": "Curitiba",
//            "destino": "Confins",
//            "dataVoo": "28/08/2018 02:00:00",
//            "valorPassagem": 0,
//            "aviao": "Air Bus"
//    },
//    {
//        "assento": "21",
//            "ocupado": true,
//            "origem": "Curitiba",
//            "destino": "Confins",
//            "dataVoo": "28/08/2018 02:00:00",
//            "valorPassagem": 0,
//            "aviao": "Air Bus"
//    },
//    {
//        "assento": "22",
//            "ocupado": true,
//            "origem": "Curitiba",
//            "destino": "Confins",
//            "dataVoo": "28/08/2018 02:00:00",
//            "valorPassagem": 0,
//            "aviao": "Air Bus"
//    },
//    {
//        "assento": "23",
//            "ocupado": true,
//            "origem": "Curitiba",
//            "destino": "Confins",
//            "dataVoo": "28/08/2018 02:00:00",
//            "valorPassagem": 0,
//            "aviao": "Air Bus"
//    },
//    {
//        "assento": "24",
//            "ocupado": true,
//            "origem": "Curitiba",
//            "destino": "Confins",
//            "dataVoo": "28/08/2018 02:00:00",
//            "valorPassagem": 0,
//            "aviao": "Air Bus"
//    },
//    {
//        "assento": "25",
//            "ocupado": true,
//            "origem": "Curitiba",
//            "destino": "Confins",
//            "dataVoo": "28/08/2018 02:00:00",
//            "valorPassagem": 0,
//            "aviao": "Air Bus"
//    },
//    {
//        "assento": "26",
//            "ocupado": true,
//            "origem": "Curitiba",
//            "destino": "Confins",
//            "dataVoo": "28/08/2018 02:00:00",
//            "valorPassagem": 0,
//            "aviao": "Air Bus"
//    },
//    {
//        "assento": "27",
//            "ocupado": true,
//            "origem": "Curitiba",
//            "destino": "Confins",
//            "dataVoo": "28/08/2018 02:00:00",
//            "valorPassagem": 0,
//            "aviao": "Air Bus"
//    },
//    {
//        "assento": "28",
//            "ocupado": true,
//            "origem": "Curitiba",
//            "destino": "Confins",
//            "dataVoo": "28/08/2018 02:00:00",
//            "valorPassagem": 0,
//            "aviao": "Air Bus"
//    },
//    {
//        "assento": "29",
//            "ocupado": false,
//            "origem": "Curitiba",
//            "destino": "Confins",
//            "dataVoo": "28/08/2018 02:00:00",
//            "valorPassagem": 0,
//            "aviao": "Air Bus"
//    },
//    {
//        "assento": "30",
//            "ocupado": true,
//            "origem": "Curitiba",
//            "destino": "Confins",
//            "dataVoo": "28/08/2018 02:00:00",
//            "valorPassagem": 0,
//            "aviao": "Air Bus"
//    },
//    {
//        "assento": "31",
//            "ocupado": true,
//            "origem": "Curitiba",
//            "destino": "Confins",
//            "dataVoo": "28/08/2018 02:00:00",
//            "valorPassagem": 0,
//            "aviao": "Air Bus"
//    },
//    {
//        "assento": "32",
//            "ocupado": true,
//            "origem": "Curitiba",
//            "destino": "Confins",
//            "dataVoo": "28/08/2018 02:00:00",
//            "valorPassagem": 0,
//            "aviao": "Air Bus"
//    },
//    {
//        "assento": "33",
//            "ocupado": true,
//            "origem": "Curitiba",
//            "destino": "Confins",
//            "dataVoo": "28/08/2018 02:00:00",
//            "valorPassagem": 0,
//            "aviao": "Air Bus"
//    },
//    {
//        "assento": "34",
//            "ocupado": true,
//            "origem": "Curitiba",
//            "destino": "Confins",
//            "dataVoo": "28/08/2018 02:00:00",
//            "valorPassagem": 0,
//            "aviao": "Air Bus"
//    },
//    {
//        "assento": "35",
//            "ocupado": true,
//            "origem": "Curitiba",
//            "destino": "Confins",
//            "dataVoo": "28/08/2018 02:00:00",
//            "valorPassagem": 0,
//            "aviao": "Air Bus"
//    },
//    {
//        "assento": "36",
//            "ocupado": true,
//            "origem": "Curitiba",
//            "destino": "Confins",
//            "dataVoo": "28/08/2018 02:00:00",
//            "valorPassagem": 0,
//            "aviao": "Air Bus"
//    },
//    {
//        "assento": "37",
//            "ocupado": true,
//            "origem": "Curitiba",
//            "destino": "Confins",
//            "dataVoo": "28/08/2018 02:00:00",
//            "valorPassagem": 0,
//            "aviao": "Air Bus"
//    },
//    {
//        "assento": "38",
//            "ocupado": false,
//            "origem": "Curitiba",
//            "destino": "Confins",
//            "dataVoo": "28/08/2018 02:00:00",
//            "valorPassagem": 0,
//            "aviao": "Air Bus"
//    },
//    {
//        "assento": "39",
//            "ocupado": false,
//            "origem": "Curitiba",
//            "destino": "Confins",
//            "dataVoo": "28/08/2018 02:00:00",
//            "valorPassagem": 0,
//            "aviao": "Air Bus"
//    },
//    {
//        "assento": "40",
//            "ocupado": true,
//            "origem": "Curitiba",
//            "destino": "Confins",
//            "dataVoo": "28/08/2018 02:00:00",
//            "valorPassagem": 0,
//            "aviao": "Air Bus"
//    },
//    {
//        "assento": "41",
//            "ocupado": false,
//            "origem": "Curitiba",
//            "destino": "Confins",
//            "dataVoo": "28/08/2018 02:00:00",
//            "valorPassagem": 0,
//            "aviao": "Air Bus"
//    },
//    {
//        "assento": "42",
//            "ocupado": false,
//            "origem": "Curitiba",
//            "destino": "Confins",
//            "dataVoo": "28/08/2018 02:00:00",
//            "valorPassagem": 0,
//            "aviao": "Air Bus"
//    },
//    {
//        "assento": "43",
//            "ocupado": true,
//            "origem": "Curitiba",
//            "destino": "Confins",
//            "dataVoo": "28/08/2018 02:00:00",
//            "valorPassagem": 0,
//            "aviao": "Air Bus"
//    },
//    {
//        "assento": "44",
//            "ocupado": false,
//            "origem": "Curitiba",
//            "destino": "Confins",
//            "dataVoo": "28/08/2018 02:00:00",
//            "valorPassagem": 0,
//            "aviao": "Air Bus"
//    },
//    {
//        "assento": "45",
//            "ocupado": false,
//            "origem": "Curitiba",
//            "destino": "Confins",
//            "dataVoo": "28/08/2018 02:00:00",
//            "valorPassagem": 0,
//            "aviao": "Air Bus"
//    },
//    {
//        "assento": "46",
//            "ocupado": true,
//            "origem": "Curitiba",
//            "destino": "Confins",
//            "dataVoo": "28/08/2018 02:00:00",
//            "valorPassagem": 0,
//            "aviao": "Air Bus"
//    },
//    {
//        "assento": "47",
//            "ocupado": true,
//            "origem": "Curitiba",
//            "destino": "Confins",
//            "dataVoo": "28/08/2018 02:00:00",
//            "valorPassagem": 0,
//            "aviao": "Air Bus"
//    },
//    {
//        "assento": "48",
//            "ocupado": false,
//            "origem": "Curitiba",
//            "destino": "Confins",
//            "dataVoo": "28/08/2018 02:00:00",
//            "valorPassagem": 0,
//            "aviao": "Air Bus"
//    },
//    {
//        "assento": "49",
//            "ocupado": false,
//            "origem": "Curitiba",
//            "destino": "Confins",
//            "dataVoo": "28/08/2018 02:00:00",
//            "valorPassagem": 0,
//            "aviao": "Air Bus"
//    },
//    {
//        "assento": "50",
//            "ocupado": true,
//            "origem": "Curitiba",
//            "destino": "Confins",
//            "dataVoo": "28/08/2018 02:00:00",
//            "valorPassagem": 0,
//            "aviao": "Air Bus"
//    },
//    {
//        "assento": "51",
//            "ocupado": false,
//            "origem": "Curitiba",
//            "destino": "Confins",
//            "dataVoo": "28/08/2018 02:00:00",
//            "valorPassagem": 0,
//            "aviao": "Air Bus"
//    },
//    {
//        "assento": "52",
//            "ocupado": true,
//            "origem": "Curitiba",
//            "destino": "Confins",
//            "dataVoo": "28/08/2018 02:00:00",
//            "valorPassagem": 0,
//            "aviao": "Air Bus"
//    },
//    {
//        "assento": "53",
//            "ocupado": false,
//            "origem": "Curitiba",
//            "destino": "Confins",
//            "dataVoo": "28/08/2018 02:00:00",
//            "valorPassagem": 0,
//            "aviao": "Air Bus"
//    },
//    {
//        "assento": "54",
//            "ocupado": true,
//            "origem": "Curitiba",
//            "destino": "Confins",
//            "dataVoo": "28/08/2018 02:00:00",
//            "valorPassagem": 0,
//            "aviao": "Air Bus"
//    },
//    {
//        "assento": "55",
//            "ocupado": false,
//            "origem": "Curitiba",
//            "destino": "Confins",
//            "dataVoo": "28/08/2018 02:00:00",
//            "valorPassagem": 0,
//            "aviao": "Air Bus"
//    },
//    {
//        "assento": "56",
//            "ocupado": true,
//            "origem": "Curitiba",
//            "destino": "Confins",
//            "dataVoo": "28/08/2018 02:00:00",
//            "valorPassagem": 0,
//            "aviao": "Air Bus"
//    },
//    {
//        "assento": "57",
//            "ocupado": true,
//            "origem": "Curitiba",
//            "destino": "Confins",
//            "dataVoo": "28/08/2018 02:00:00",
//            "valorPassagem": 0,
//            "aviao": "Air Bus"
//    },
//    {
//        "assento": "58",
//            "ocupado": false,
//            "origem": "Curitiba",
//            "destino": "Confins",
//            "dataVoo": "28/08/2018 02:00:00",
//            "valorPassagem": 0,
//            "aviao": "Air Bus"
//    },
//    {
//        "assento": "59",
//            "ocupado": false,
//            "origem": "Curitiba",
//            "destino": "Confins",
//            "dataVoo": "28/08/2018 02:00:00",
//            "valorPassagem": 0,
//            "aviao": "Air Bus"
//    },
//    {
//        "assento": "60",
//            "ocupado": true,
//            "origem": "Curitiba",
//            "destino": "Confins",
//            "dataVoo": "28/08/2018 02:00:00",
//            "valorPassagem": 0,
//            "aviao": "Air Bus"
//    },
//    {
//        "assento": "61",
//            "ocupado": true,
//            "origem": "Curitiba",
//            "destino": "Confins",
//            "dataVoo": "28/08/2018 02:00:00",
//            "valorPassagem": 0,
//            "aviao": "Air Bus"
//    },
//    {
//        "assento": "62",
//            "ocupado": false,
//            "origem": "Curitiba",
//            "destino": "Confins",
//            "dataVoo": "28/08/2018 02:00:00",
//            "valorPassagem": 0,
//            "aviao": "Air Bus"
//    },
//    {
//        "assento": "63",
//            "ocupado": false,
//            "origem": "Curitiba",
//            "destino": "Confins",
//            "dataVoo": "28/08/2018 02:00:00",
//            "valorPassagem": 0,
//            "aviao": "Air Bus"
//    },
//    {
//        "assento": "64",
//            "ocupado": false,
//            "origem": "Curitiba",
//            "destino": "Confins",
//            "dataVoo": "28/08/2018 02:00:00",
//            "valorPassagem": 0,
//            "aviao": "Air Bus"
//    },
//    {
//        "assento": "65",
//            "ocupado": false,
//            "origem": "Curitiba",
//            "destino": "Confins",
//            "dataVoo": "28/08/2018 02:00:00",
//            "valorPassagem": 0,
//            "aviao": "Air Bus"
//    },
//    {
//        "assento": "66",
//            "ocupado": true,
//            "origem": "Curitiba",
//            "destino": "Confins",
//            "dataVoo": "28/08/2018 02:00:00",
//            "valorPassagem": 0,
//            "aviao": "Air Bus"
//    },
//    {
//        "assento": "67",
//            "ocupado": false,
//            "origem": "Curitiba",
//            "destino": "Confins",
//            "dataVoo": "28/08/2018 02:00:00",
//            "valorPassagem": 0,
//            "aviao": "Air Bus"
//    },
//    {
//        "assento": "68",
//            "ocupado": false,
//            "origem": "Curitiba",
//            "destino": "Confins",
//            "dataVoo": "28/08/2018 02:00:00",
//            "valorPassagem": 0,
//            "aviao": "Air Bus"
//    },
//    {
//        "assento": "69",
//            "ocupado": true,
//            "origem": "Curitiba",
//            "destino": "Confins",
//            "dataVoo": "28/08/2018 02:00:00",
//            "valorPassagem": 0,
//            "aviao": "Air Bus"
//    },
//    {
//        "assento": "70",
//            "ocupado": false,
//            "origem": "Curitiba",
//            "destino": "Confins",
//            "dataVoo": "28/08/2018 02:00:00",
//            "valorPassagem": 0,
//            "aviao": "Air Bus"
//    },
//    {
//        "assento": "71",
//            "ocupado": false,
//            "origem": "Curitiba",
//            "destino": "Confins",
//            "dataVoo": "28/08/2018 02:00:00",
//            "valorPassagem": 0,
//            "aviao": "Air Bus"
//    },
//    {
//        "assento": "72",
//            "ocupado": false,
//            "origem": "Curitiba",
//            "destino": "Confins",
//            "dataVoo": "28/08/2018 02:00:00",
//            "valorPassagem": 0,
//            "aviao": "Air Bus"
//    },
//    {
//        "assento": "73",
//            "ocupado": true,
//            "origem": "Curitiba",
//            "destino": "Confins",
//            "dataVoo": "28/08/2018 02:00:00",
//            "valorPassagem": 0,
//            "aviao": "Air Bus"
//    },
//    {
//        "assento": "74",
//            "ocupado": false,
//            "origem": "Curitiba",
//            "destino": "Confins",
//            "dataVoo": "28/08/2018 02:00:00",
//            "valorPassagem": 0,
//            "aviao": "Air Bus"
//    },
//    {
//        "assento": "75",
//            "ocupado": false,
//            "origem": "Curitiba",
//            "destino": "Confins",
//            "dataVoo": "28/08/2018 02:00:00",
//            "valorPassagem": 0,
//            "aviao": "Air Bus"
//    },
//    {
//        "assento": "76",
//            "ocupado": false,
//            "origem": "Curitiba",
//            "destino": "Confins",
//            "dataVoo": "28/08/2018 02:00:00",
//            "valorPassagem": 0,
//            "aviao": "Air Bus"
//    },
//    {
//        "assento": "77",
//            "ocupado": true,
//            "origem": "Curitiba",
//            "destino": "Confins",
//            "dataVoo": "28/08/2018 02:00:00",
//            "valorPassagem": 0,
//            "aviao": "Air Bus"
//    },
//    {
//        "assento": "78",
//            "ocupado": true,
//            "origem": "Curitiba",
//            "destino": "Confins",
//            "dataVoo": "28/08/2018 02:00:00",
//            "valorPassagem": 0,
//            "aviao": "Air Bus"
//    },
//    {
//        "assento": "79",
//            "ocupado": false,
//            "origem": "Curitiba",
//            "destino": "Confins",
//            "dataVoo": "28/08/2018 02:00:00",
//            "valorPassagem": 0,
//            "aviao": "Air Bus"
//    },
//    {
//        "assento": "80",
//            "ocupado": true,
//            "origem": "Curitiba",
//            "destino": "Confins",
//            "dataVoo": "28/08/2018 02:00:00",
//            "valorPassagem": 0,
//            "aviao": "Air Bus"
//    },
//    {
//        "assento": "81",
//            "ocupado": true,
//            "origem": "Curitiba",
//            "destino": "Confins",
//            "dataVoo": "28/08/2018 02:00:00",
//            "valorPassagem": 0,
//            "aviao": "Air Bus"
//    },
//    {
//        "assento": "82",
//            "ocupado": false,
//            "origem": "Curitiba",
//            "destino": "Confins",
//            "dataVoo": "28/08/2018 02:00:00",
//            "valorPassagem": 0,
//            "aviao": "Air Bus"
//    },
//    {
//        "assento": "83",
//            "ocupado": true,
//            "origem": "Curitiba",
//            "destino": "Confins",
//            "dataVoo": "28/08/2018 02:00:00",
//            "valorPassagem": 0,
//            "aviao": "Air Bus"
//    },
//    {
//        "assento": "84",
//            "ocupado": false,
//            "origem": "Curitiba",
//            "destino": "Confins",
//            "dataVoo": "28/08/2018 02:00:00",
//            "valorPassagem": 0,
//            "aviao": "Air Bus"
//    },
//    {
//        "assento": "85",
//            "ocupado": true,
//            "origem": "Curitiba",
//            "destino": "Confins",
//            "dataVoo": "28/08/2018 02:00:00",
//            "valorPassagem": 0,
//            "aviao": "Air Bus"
//    },
//    {
//        "assento": "86",
//            "ocupado": true,
//            "origem": "Curitiba",
//            "destino": "Confins",
//            "dataVoo": "28/08/2018 02:00:00",
//            "valorPassagem": 0,
//            "aviao": "Air Bus"
//    },
//    {
//        "assento": "87",
//            "ocupado": true,
//            "origem": "Curitiba",
//            "destino": "Confins",
//            "dataVoo": "28/08/2018 02:00:00",
//            "valorPassagem": 0,
//            "aviao": "Air Bus"
//    },
//    {
//        "assento": "88",
//            "ocupado": true,
//            "origem": "Curitiba",
//            "destino": "Confins",
//            "dataVoo": "28/08/2018 02:00:00",
//            "valorPassagem": 0,
//            "aviao": "Air Bus"
//    },
//    {
//        "assento": "89",
//            "ocupado": true,
//            "origem": "Curitiba",
//            "destino": "Confins",
//            "dataVoo": "28/08/2018 02:00:00",
//            "valorPassagem": 0,
//            "aviao": "Air Bus"
//    }
//]


    @Headers({ "Accept: application/json" })
    @GET("voo/{id_voo}/poltronas/{id_poltrona}")
    Call<Poltrona> getPoltronas(@Path("id_voo") long id_voo,
                                @Path("id_poltrona") long id_poltrona,
                                @Header("code") String code);
//    {
//        "id": 1,
//        "assento": "1",
//        "ocupado": true,
//        "usuario": {
//            "id": 7,
//            "nome": "Adilson Souza",
//            "email": "adilson.carmo@viannasempre.com.br",
//            "login": "adilson",
//            "ativo": true
//        }
//    }


    @Headers({ "Accept: application/json" })
    @POST("/voo/{id_voo}/poltronas/{id_poltrona}")
    Call<Poltrona> postSavePoltrona(@Path("id_voo") long id_voo,
                                   @Path("id_poltrona") long id_poltrona,
                                   @Header("code") String code);
    // id_voo = 1
    // id_poltrona = 29
    // Para dar certo o assento precisa ter OCUPADO == FALSE
//    {
//        "id": 29,
//        "assento": "29",
//        "ocupado": true,
//        "usuario": {
//            "id": 135,
//            "nome": "aaa",
//            "email": "aaa",
//            "login": "aaa",
//            "ativo": true
//        }
//    }


    /**----------------------------------  CARTAO  -------------------------------------*/
    @Headers({ "Accept: application/json" })
    @GET("cartao/{cartao}/{mes}/{ano}/{tarja}/{valor}")
    Call<Poltrona> getPoltronas(@Path("cartao") String cartao,
                                @Path("mes") int mes,
                                @Path("ano") int ano,
                                @Path("tarja") String tarja,
                                @Path("valor") double valor);

}
