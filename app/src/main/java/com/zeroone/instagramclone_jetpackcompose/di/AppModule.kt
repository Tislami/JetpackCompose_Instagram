package com.zeroone.instagramclone_jetpackcompose.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    /*
    @Provides
    @Singleton
    fun provideFirebaseDatabase(): FirebaseDatabase = FirebaseDatabase()



    @Provides
    @Singleton
    fun provideAuthRepository(firebaseDatabase: FirebaseDatabase,) : AuthRepository {
        return AuthRepositoryImpl(
            firebaseDatabase.auth,
            firebaseDatabase.userCollection,
            firebaseDatabase.userStorageRef
        )
    }

    @Provides
    @Singleton
    fun providePostRepository(firebaseDatabase: FirebaseDatabase,) : PostRepository {
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
    fun provideUserRepository(firebaseDatabase: FirebaseDatabase,) : UserRepository {
        return UserRepositoryImpl(
            firebaseDatabase.auth,
            firebaseDatabase.firestore,
            firebaseDatabase.userCollection,
            firebaseDatabase.postCollection
        )
    }

    @Provides
    @Singleton
    fun provideMainRepository(firebaseDatabase: FirebaseDatabase,) : MainRepository {
        return MainRepositoryImpl(firebaseDatabase)
    }

    @Provides
    @Singleton
    fun provideUserViewModel(useCase: UseCase) : UserViewModel {
        return UserViewModel(useCase = useCase)
    }

    @Provides
    @Singleton
    fun provideAuthViewModel(useCase: UseCase,userViewModel: UserViewModel) : AuthViewModel {
        return AuthViewModel(useCase = useCase, userViewModel = userViewModel)
    }



    @Provides
    @Singleton
    fun provideUseCase(
        authRepository: AuthRepository,
        userRepository: UserRepository,
        postRepository: PostRepository,
        mainRepository: MainRepository,
    ): UseCase{
        return UseCase(
            createUser = CreateUser(repository = authRepository),
            setUser = SetUser(repository = authRepository),
            login = Login(repository = authRepository),
            getAuthState = GetAuthState(repository = authRepository),
            setPhoto = SetPhoto(repository = authRepository),
            getUser = GetUser(repository = userRepository),
            signOut = SignOut(repository = authRepository),
            setPostPhoto = SetPostPhoto(repository = postRepository),
            savePost = SavePost(repository = postRepository),
            updateUser = UpdateUser(repository = userRepository),
            getUserPost = GetUserPosts(repository = userRepository),
            getPosts = GetPosts(repository = postRepository),
            searchUser = SearchUser(repository = mainRepository),
            follow = Follow(repository = userRepository),
            unFollow = UnFollow(repository = userRepository),
            getUsers = GetUsers(repository = userRepository),
            getFollowing = GetFollowing(repository = userRepository)
        )
    }

*/
}