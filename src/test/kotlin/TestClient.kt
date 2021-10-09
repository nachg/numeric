import org.numeric.CalcAlg
import kotlin.test.Test
import kotlin.test.assertEquals

class TestClient {
    @Test
    fun sumAllDigits() {
        assertEquals(
            "1 + 9 = 10 = 1 + 0 = 1",
            CalcAlg.sumAllDigits("19")
        )
    }
} 