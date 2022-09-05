package com.zeroone.instagramclone_jetpackcompose.di

import com.zeroone.instagramclone_jetpackcompose.data.FirebaseDatabase
import com.zeroone.instagramclone_jetpackcompose.domain.repository.*
import com.zeroone.instagramclone_jetpackcompose.domain.use_case.UseCase
import com.zeroone.instagramclone_jetpackcompose.domain.use_case.auth.*
import com.zeroone.instagramclone_jetpackcompose.domain.use_case.post.GetUserPosts
import com.zeroone.instagramclone_jetpackcompose.domain.use_case.post.PostUseCase
import com.zeroone.instagramclone_jetpackcompose.domain.use_case.post.SetPost
import com.zeroone.instagramclone_jetpackcompose.domain.use_case.post.SetPostPhoto
import com.zeroone.instagramclone_jetpackcompose.domain.use_case.user.GetUser
import com.zeroone.instagramclone_jetpackcompose.domain.use_case.user.SetUser
import com.zeroone.instagramclone_jetpackcompose.domain.use_case.user.UserUseCase
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.auth.AuthViewModel
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.newpost.NewPostViewModel
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
            auth = firebaseDatabase.auth,
            userCollection = firebaseDatabase.userCollection
        )
    }

    @Provides
    @Singleton
    fun providePostRepository(firebaseDatabase: FirebaseDatabase): PostRepository {
        return PostRepositoryImpl(
            firebaseDatabase.auth,
            firebaseDatabase.firestore,
            firebaseDatabase.userCollection,
            firebaseDatabase.postCollection,
            firebaseDatabase.postStorageRef
        )
    }


    @Provides
    @Singleton
    fun provideAuthViewModel(useCase: UseCase): AuthViewModel {
        return AuthViewModel(useCase = useCase)
    }

    @Provides
    @Singleton
    fun provideUserViewModel(useCase: UseCase): UserViewModel {
        return UserViewModel(useCase = useCase)
    }

    @Provides
    @Singleton
    fun provideNewPostViewModel(useCase: UseCase, userViewModel: UserViewModel): NewPostViewModel {
        return NewPostViewModel(
            useCase = useCase,
            userViewModel = userViewModel
        )
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
    fun provideUserUseCase(userRepository: UserRepository): UserUseCase {
        return UserUseCase(
            getUser = GetUser(userRepository),
            setUser = SetUser(userRepository),
        )
    }

    @Provides
    @Singleton
    fun providePosUseCase(postRepository: PostRepository): PostUseCase {
        return PostUseCase(
            setPost = SetPost(postRepository),
            setPostPhoto = SetPostPhoto(postRepository),
            getUserPosts = GetUserPosts(postRepository)
        )
    }


    @Provides
    @Singleton
    fun provideUseCase(
        authUseCase: AuthUseCase,
        userUseCase: UserUseCase,
        postUseCase: PostUseCase
    ): UseCase {
        return UseCase(
            authUseCase = authUseCase,
            userUseCase = userUseCase,
            postUseCase = postUseCase
        )
    }
}