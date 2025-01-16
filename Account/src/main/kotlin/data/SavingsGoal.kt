package data

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import lombok.Data
import java.time.LocalDate

@Entity
@Data
data class SavingsGoal(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int,
    var name: String,
    var targetAmount: Int,
    var targetDate: LocalDate,
    var currentAmount: Int
) {
}