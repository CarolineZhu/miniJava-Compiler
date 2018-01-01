class test{
    public static void main(String[] a) {
        System.out.println(new Fac().ComputeFac(10));
    }
}

class A {
    public int foo () {
        return 1;
    }
}

class Fac {

    public int ComputeFac(int num){
        int num_aux ;
        int a;
        A app;
        if (num < 1)
            num_aux = 1 ;
        else
            num_aux = num * (this.ComputeFac(num-1)) ;
        a = app.ComputeFac();
        a = app.Blabla();
        return num_aux ;
    }

}