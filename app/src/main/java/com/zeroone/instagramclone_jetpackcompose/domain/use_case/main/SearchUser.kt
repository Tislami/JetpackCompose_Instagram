package com.zeroone.instagramclone_jetpackcompose.domain.use_case.main


import com.zeroone.instagramclone_jetpackcompose.domain.repository.MainRepository

class SearchUser(private val repository: MainRepository) {
    operator fun invoke(query: String) =
        repository.searchUser(query)
}