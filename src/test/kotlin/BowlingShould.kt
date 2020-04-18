import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class GameShould{

    private var game = Game()

    @Test
    fun return_one_when_one_pin(){
        game.roll(1)
        game.roll(0)

        assertThat(game.score()).isEqualTo(1)
    }

    @Test
    fun return_two_when_two_pins(){
        game.roll(2)
        game.roll(0)

        assertThat(game.score()).isEqualTo(2)
    }

    @Test
    fun return_two_when_twice_one_pin(){
        game.roll(1)
        game.roll(1)

        assertThat(game.score()).isEqualTo(2)
    }

    @Test
    fun return_result_when_two_normal_frames(){
        // 1st frame
        game.roll(1)
        game.roll(1)

        // 2nd frame
        game.roll(2)
        game.roll(2)

        assertThat(game.score()).isEqualTo(6)
    }


    @Test
    fun return_result_when_spares_followed_by_0(){
        // 1st frame
        game.roll(9)
        game.roll(1)

        // 2nd frame
        game.roll(0)        // no bonus !
        game.roll(0)

        assertThat(game.score()).isEqualTo(10)
    }


    @Test
    fun return_result_when_spare(){
        // 1st frame : spare
        game.roll(9)
        game.roll(1)

        // 2nd frame
        game.roll(1)        // bonus, should be counted twice
        game.roll(0)

        // 10 + 1 bonus from next roll + 1
        assertThat(game.score()).isEqualTo(12)
    }

    @Test
    fun return_not_count_second_roll_from_frame_as_bonus_after_spare(){
        // 1st frame : spare
        game.roll(9)
        game.roll(1)

        // 2nd frame
        game.roll(1)        // bonus, should be counted twice
        game.roll(1)        // should be counted once

        // 10 + 1 bonus from next roll + 2
        assertThat(game.score()).isEqualTo(13)
    }


    @Test
    fun return_not_count_second_roll_from_frame_as_bonus_after_spare_if_first_roll_is_0(){
        // 1st frame : spare
        game.roll(9)
        game.roll(1)

        // 2nd frame
        game.roll(0)        // bonus
        game.roll(1)        // should be counted once

        // 10 + 0 bonus from next roll + 1
        assertThat(game.score()).isEqualTo(11)
    }

    @Test
    fun return_count_bonus_for_strike(){
        // 1st frame : spare
        game.roll(10)

        // 2nd frame
        game.roll(1)        // bonus
        game.roll(2)        // should be counted once

        // 10 + 3 bonus from next roll + 3
        assertThat(game.score()).isEqualTo(16)
    }


}

