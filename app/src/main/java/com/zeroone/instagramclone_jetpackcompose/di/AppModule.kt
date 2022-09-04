package com.zeroone.instagramclone_jetpackcompose.di

import com.zeroone.instagramclone_jetpackcompose.data.FirebaseDatabase
import com.zeroone.instagramclone_jetpackcompose.domain.repository.AuthRepository
import com.zeroone.instagramclone_jetpackcompose.domain.repository.AuthRepositoryImpl
import com.zeroone.instagramclone_jetpackcompose.domain.repository.UserRepository
import com.zeroone.instagramclone_jetpackcompose.domain.repository.UserRepositoryImpl
import com.zeroone.instagramclone_jetpackcompose.domain.use_case.UseCase
import com.zeroone.instagramclone_jetpackcompose.domain.use_case.auth.*
import com.zeroone.instagramclone_jetpackcompose.domain.use_case.user.GetUser
import com.zeroone.instagramclone_jetpackcompose.domain.use_case.user.SetUser
import com.zeroone.instagramclone_jetpackcompose.domain.use_case.user.UserUseCase
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.auth.AuthViewModel
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.user.UserViewModel
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
    fun provideUserRepository(firebaseDatabase: FirebaseDatabase): UserRepository {
        return UserRepositoryImpl(
            userCollection = firebaseDatabase.userCollection
        )
    }


    @Provides
    @Singleton
    fun provideAuthViewModel(useCase: UseCase, userViewModel: UserViewModel): AuthViewModel {
        return AuthViewModel(useCase = useCase)
    }

    @Provides
    @Singleton
    fun provideUserViewModel(useCase: UseCase): UserViewModel {
        return UserViewModel(useCase = useCase)
    }


    @Provides
    @Singleton
    fun provideAuthUseCase(authRepository: AuthRepository): AuthUseCase {
        return AuthUseCase(
            createUser = CreateUser(repository = authRepository),
            login = Login(repository = authRepository),
            singOut = SignOut(repository = authRepository),
            getAuthState = GetAuthState(repository = authRepository),
        )
    }

    @Provides
    @Singleton
    fun provideUserUseCase(userRepository: UserRepository): UserUseCase {
        return UserUseCase(
            getUser = GetUser(userRepository = userRepository),
            setUser = SetUser(userRepository = userRepository),
        )
    }


    @Provides
    @Singleton
    fun provideUseCase(
        authUseCase: AuthUseCase,
        userUseCase: UserUseCase
    ): UseCase {
        return UseCase(
            authUseCase = authUseCase,
            userUseCase = userUseCase
        )
    }
}