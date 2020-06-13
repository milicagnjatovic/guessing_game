public enum Colors {
    RED(0),
    GREEN(1),
    BLUE(2),
    YELLOW(3),
    ORANGE(4),
    PURPLE(5),
    ERR(-1);

    int num;
    private Colors(int i){
        num = i;
    }


    public static Colors[] arrColors = {RED, GREEN, BLUE, YELLOW, ORANGE, PURPLE};
}
