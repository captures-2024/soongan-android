package com.captures2024.soongan.core.data.remote.impl

import com.captures2024.soongan.core.data.remote.MembersDataSource
import com.captures2024.soongan.core.data.service.MembersService
import javax.inject.Inject

class MembersDataSourceImpl
@Inject
constructor(
    private val service: MembersService,
) : MembersDataSource {

}
