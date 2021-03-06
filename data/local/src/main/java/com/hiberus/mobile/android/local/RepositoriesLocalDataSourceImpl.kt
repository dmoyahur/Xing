package com.hiberus.mobile.android.local

import com.hiberus.mobile.android.local.mapper.mapFromCached
import com.hiberus.mobile.android.local.mapper.mapToCached
import com.hiberus.mobile.android.model.repositories.Repository
import com.hiberus.mobile.android.repository.datasource.repositories.RepositoriesLocalDataSource

class RepositoriesLocalDataSourceImpl(
    private val repositoriesDao: RepositoriesDao
) : RepositoriesLocalDataSource {

    override suspend fun getRepositories(offset: Int, limit: Int): List<Repository> =
        repositoriesDao.getRepositories(offset, limit)?.mapFromCached() ?: emptyList()

    override suspend fun saveRepositories(repositories: List<Repository>) =
        repositoriesDao.insertRepositories(repositories.mapToCached())

    override suspend fun savePage(page: Int) =
        repositoriesDao.insertPage(page.mapToCached())

    override suspend fun isPageUpdated(page: Int): Boolean =
        repositoriesDao.getPage()?.let {
            it > page
        } ?: false
}