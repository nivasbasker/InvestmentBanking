package repo

import data.Account
import org.springframework.data.jpa.repository.JpaRepository

interface AccountRepo : JpaRepository<Account, Int> {
}