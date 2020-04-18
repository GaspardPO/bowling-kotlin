class Game {
    private var frames = ArrayList<Frame>();
    private var currentFrame = Frame()

    fun roll(falled: Int) {
        currentFrame.addRoll(falled)

        if (currentFrame.is_finished()){
            frames.add(currentFrame)
            currentFrame = Frame()
        }
    }

    fun score(): Int {
        var score = 0
        for ((index, frame) in frames.withIndex()) {
            if (frame.isStrike()) {
                score += frames[index+1].roll1
                score += frames[index+1].roll2
            }
            else if (frame.isSpare()) score += frames[index+1].roll1
            score += frame.score()
        }

        return score
    }

}

class Frame {
    var roll1: Int = 0
    var roll2: Int = 0
    private var nb_of_rolls = 0

    fun addRoll(falled: Int) {
        if (nb_of_rolls == 0){
            roll1 = falled
        }else{
            roll2 = falled
        }
        nb_of_rolls ++
    }

    fun is_finished(): Boolean {
        return nb_of_rolls == 2 || score() == 10
    }
    fun score(): Int = roll1 + roll2
    fun isSpare(): Boolean {
        return score() == 10 && nb_of_rolls == 2
    }
    fun isStrike(): Boolean = roll1 == 10

}
