package hr.ferit.kristiandzoic.lv5

class Robot(private var x : Int, private var y : Int) {
    fun goLeft(steps : Int){
        x -= steps
    }
    fun goRight(steps : Int){
        x += steps
    }
    fun goUp(steps : Int){
        y += steps
    }
    fun goDown(steps : Int){
        y -= steps
    }
    fun getLocation(){
        println("$x,$y")
    }
}