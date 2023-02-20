package com.zeroone.jetpackcompose_instagram.di

import com.zeroone.jetpackcompose_instagram.domain.use_case.UseCase
import com.zeroone.jetpackcompose_instagram.data.FirebaseDatabase
import com.zeroone.jetpackcompose_instagram.domain.repository.*
import com.zeroone.jetpackcompose_instagram.domain.use_case.auth.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideFirebaseDatabase(): FirebaseDatabase = FirebaseDatabase()


    @Provides
    @Singleton
    fun provideAuthRepository(firebaseDatabase: FirebaseDatabase): AuthRepository {
        return AuthRepositoryImpl(firebaseDatabase.auth)
    }


    @Provides
    @Singleton
    fun provideAuthUseCase(authRepository: AuthRepository): AuthUseCase {
        return AuthUseCase(
            createUser = CreateUser(authRepository),
            login = Login(authRepository),
            singOut = SignOut(authRepository),
            getAuthState = GetAuthState(authRepository),
        )
    }

    @Provides
    @Singleton
    fun provideUseCase(
        authUseCase: AuthUseCase,
    ): UseCase {
        return UseCase(
            authUseCase = authUseCase,
        )
    }
}