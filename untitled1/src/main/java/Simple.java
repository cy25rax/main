public class Simple {
    @Test(value = 10)
    public void Method1 (){
        System.out.println("Method 1 Test(value = 10)");
    }

    @BeforeSuite
    public void Method2 (){
        System.out.println("Method 2 BeforeSuite");
    }

    @Test(value = 9)
//    @BeforeSuite
    public void Method3 (){
        System.out.println("Method 3 Test(value = 9)");
    }

    @Test(value = 4)
    public void Method4 (){
        System.out.println("Method 4 Test(value = 4)");
    }

    @Test(value = 4)
    public void Method5 (){
        System.out.println("Method 5 Test(value = 4)");
    }

    @Test(value = 6)
    public void Method6 (){
        System.out.println("Method 6 Test(value = 6)");
    }

    @Test(value = 5)
    public void Method7 (){
        System.out.println("Method 7 Test(value = 5)");
    }

    @Test(value = 2)
    public void Method8 (){
        System.out.println("Method 8 Test(value = 2)");
    }

    @Test(value = 3)
    //@AfterSuite
    public void Method9 (){
        System.out.println("Method 9 Test(value = 3)");
    }

    @Test(value = 2)
    public void Method10 (){
        System.out.println("Method 10 Test(value = 2)");
    }

    @AfterSuite
    public void Method11 (){
        System.out.println("Method 11 AfterSuite");
    }

    @Test(value = 1)
    public void Method12 (){
        System.out.println("Method 12 Test(value = 1)");
    }
}
