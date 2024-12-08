package com.captures2024.soongan.core.data.repository.impl

import com.captures2024.soongan.core.data.remote.MembersDataSource
import com.captures2024.soongan.core.data.repository.MembersRepository
import javax.inject.Inject

class MembersRepositoryImpl
@Inject
constructor(
    private val membersDataSource: MembersDataSource,
) : MembersRepository {

}
