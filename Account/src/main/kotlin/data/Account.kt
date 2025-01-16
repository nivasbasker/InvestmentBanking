package data

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToMany
import lombok.Data
import java.time.LocalDate

@Entity
@Data
data class Account(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int,
    var userId: Int,
    var accountNumber: String,
    var balance: Int,
    var createdAt: LocalDate,
    var updatedAt: LocalDate,
    var isActive: Boolean,
    @OneToMany(cascade = [CascadeType.REMOVE, CascadeType.PERSIST])
    @JoinColumn(name = "account_id")
    var savingsGoal: List<SavingsGoal>
)
