package com.example.kcalcul.network

import com.example.kcalcul.network.dtos.foodsBeverages.CreateRecipeDto
import com.example.kcalcul.network.dtos.foodsBeverages.CreateRecipeResponseDto
import com.example.kcalcul.network.dtos.users.LogUserResponseDto
import com.example.kcalcul.network.dtos.users.CreateUserDto
import com.example.kcalcul.network.dtos.users.CreateUserResponseDto
import com.example.kcalcul.network.dtos.users.LogUserDto
import com.example.kcalcul.network.dtos.users.UpdateUserDto
import com.example.kcalcul.network.dtos.users.UpdateUserResponseDto
import com.example.kcalcul.network.dtos.users.UserDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @POST(ApiRoutes.POST_GET_CREATE_USER)
    suspend fun createUser(
        @Body createUser : CreateUserDto
    ): Response<CreateUserResponseDto>?

    @POST(ApiRoutes.POST_LOGIN_USER)
    suspend fun logUser(
        @Body userInformations : LogUserDto
    ): Response<LogUserResponseDto>?

    @GET(ApiRoutes.POST_GET_CREATE_USER)
    suspend fun getUser(
        @Header("Authorization") authorization: String,
        @Query("id") idU: Long
    ): Response<UserDto>?

    @PATCH(ApiRoutes.PATCH_UPDATE_USER)
    suspend fun updateUser(
        @Path("id") id: Long,
        @Body updateUserDto: UpdateUserDto,
        @Header("Authorization") authorization: String
    ): Response<UpdateUserResponseDto>?

    @POST(ApiRoutes.CREATE_RECIPE)
    suspend fun createRecipe(
        @Header("Authorization") authorization: String,
        @Body recipe: CreateRecipeDto
    ): Response<CreateRecipeResponseDto>?
}