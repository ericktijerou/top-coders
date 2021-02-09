package com.ericktijerou.topcoders.domain.usecase

import com.ericktijerou.topcoders.domain.repository.UserRepository
import javax.inject.Inject

class GetUserListUseCase @Inject constructor(private val repository: UserRepository) {
    operator fun invoke(location: String) = repository.getUserListByLocation(location)
}