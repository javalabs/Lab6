package lab.yegorov;

/**
 * Created by AdminPC on 13.02.14.
 */
/*
 Задание на лабораторную работу:
	Необходимо в основном классе Java - программы создать:
1.	9 методов (один оператор по варианту задания – один метод).
2.	5 методов для проверки приоритетов (методы, в которых используется одновременно два разных
    оператора – например, «a+b*c» - результат вычисления зависит от приоритета).
3.	5 методов для проверки ассоциативности (методы, в которых один и тот же оператор используется дважды,
    и результат зависит от порядка выполнения операторов. Например: «a-b-c» - результат зависит от того,
    вычисляется ли сначала a-b или b-c).
4.	10 методов тестирования. Для каждого метода тестирования провести минимум 3 теста.
 */

public class Main {
    public static void main(String args[]) {

        try
        {
            for(int a = 10; a < 16; ++a) {
                priorityTest1(a, a + 2, a - 2);
                priorityTest2(a, a + 2, a - 2);
                priorityTest3(a, a + 2, a - 2);
            }

            priorityTest4(true, false, false);
            priorityTest5(true, false, false);

            priorityTest4(false, false, true);
            priorityTest5(false, false, true);

            priorityTest4(true, true, false);
            priorityTest5(true, true, false);

            priorityTest4(true, true, true);
            priorityTest5(true, true, true);

            priorityTest4(false, false, false);
            priorityTest5(false, false, false);


            for(int a = 10; a < 16; ++a) {
                associativityTest1(a, a + 2, a - 5);
                associativityTest2(a, a + 2, a - 5);
                associativityTest3(a, a + 2, a - 5);
            }

            associativityTest4(true, false, false);
            associativityTest5(true, false, false);

            associativityTest4(false, false, true);
            associativityTest5(false, false, true);

            associativityTest4(true, true, false);
            associativityTest5(true, true, false);

            associativityTest4(true, true, true);
            associativityTest5(true, true, true);

            associativityTest4(false, false, false);
            associativityTest5(false, false, false);

            for(int a = 5; a < 9; ++a) {
                addTest(a, a+3);
                modTest(a, a-3);
                assignmentWithSubtractionTest(a, a+3);
                orTest(a, a+3);
                xorWithAssignmentTest(a, a+3);
                moreTest(a, a+3);
            }

            boolXorTest(true, true);
            boolXorWithAssignmentTest(true, true);
            boolXorWithAssignmentTest2(true, true);

            boolXorTest(true, false);
            boolXorWithAssignmentTest(true, false);
            boolXorWithAssignmentTest2(true, false);

            boolXorTest(false, true);
            boolXorWithAssignmentTest(false, true);
            boolXorWithAssignmentTest2(false, true);

            boolXorTest(false, false);
            boolXorWithAssignmentTest(false, false);
            boolXorWithAssignmentTest2(false, false);

            boolNotTest(true);
            boolNotTest(false);

        }catch (MyException e)
        {
            System.out.println(e.message);
        }


    }
    public static int add(int a, int b) {
        return a + b;
    }
    public static int mod(int a, int b) {
        return a % b;
    }
    public static int assignmentWithSubtraction(int a, int b) {
        return a -= b;
    }
    public static int or(int a, int b) {
        return a | b;
    }
    public static int xorWithAssignment(int a, int b) {
        return a ^= b;
    }
    public static boolean more(int a, int b) {
        return a > b;
    }
    public static boolean boolXor(boolean a, boolean b) {
        return a ^ b;
    }
    public static boolean boolNot(boolean a) {
        return !a;
    }
    public static boolean boolXorWithAssignment(boolean a, boolean b) {
        return a ^= b;
    }

    //-------//

    public static void priorityTest1(int a, int b, int c) throws MyException {
        int t;
        if((t = add(a,mod(b,c))) == (a + b % c))
            System.out.println("Priority operators \"a + b % c\" "+ t +" is work!");
        else
            throw new MyException("Priority operators \"a + b % c\" not working correctly!");
    }

    public static void priorityTest2(int a, int b, int c) throws MyException {
        int t;
        if((t = assignmentWithSubtraction(a, add(b, c))) == (a -= b + c))
            System.out.println("Priority operators \"a -= b + c\" "+ t +" is work!");
        else
            throw new MyException("Priority operators \"a -= b + c\" not working correctly!");
    }

    public static void priorityTest3(int a, int b, int c) throws MyException {
        int t;
        if((t = xorWithAssignment(a, or(b, c))) == (a ^= b | c))
            System.out.println("Priority operators \"a ^= b | c\" "+ t +" is work!");
        else
            throw new MyException("Priority operators \"a ^= b | c\" not working correctly!");
    }

    public static void priorityTest4(boolean a, boolean b, boolean c) throws MyException {
        boolean t;
        if((t = boolXorWithAssignment(a, boolXor(b, c))) == (a ^= b ^ c))
            System.out.println("Priority operators \"a ^= b ^ c\" "+ t +" is work!");
        else
            throw new MyException("Priority operators \"a ^= b ^ c\" not working correctly!");
    }

    public static void priorityTest5(boolean a, boolean b, boolean c) throws MyException {
        boolean t;
        if((t = boolXorWithAssignment(a, boolXor(boolNot(b), boolNot(c)))) == (a ^= !b ^ !c))
            System.out.println("Priority operators \"a ^= !b ^ !c\" "+ t +" is work!");
        else
            throw new MyException("Priority operators \"a ^= !b ^ !c\" not working correctly!");
    }

    //-----//

    public static void associativityTest1(int a, int b, int c) throws MyException {
        int t;
        if((t = mod(mod(a, b), c)) == (a % b % c))
            System.out.println("Associativity operators \"a % b % c\" "+ t +" is work!");
        else
            throw new MyException("Associativity operators \"a % b % c\" not working correctly!");
    }

    public static void associativityTest2(int a, int b, int c) throws MyException {
        int t;
        if((t = assignmentWithSubtraction(a, assignmentWithSubtraction(b, c))) == (a -= b -= c))
            System.out.println("Associativity operators \"a -= b -= c\" "+ t +" is work!");
        else
            throw new MyException("Associativity operators \"a -= b -= c\" not working correctly!");
    }

    public static void associativityTest3(int a, int b, int c) throws MyException {
        int t;
        if((t = xorWithAssignment(a, xorWithAssignment(b, c))) == (a ^= b ^= c))
            System.out.println("Associativity operators \"a ^= b ^= c\" "+ t +" is work!");
        else
            throw new MyException("Associativity operators \"a ^= b ^= c\" not working correctly!");
    }

    public static void associativityTest4(boolean a, boolean b, boolean c) throws MyException {
        boolean t;
        if((t = boolXor(boolXor(a, b), c)) == (a ^ b ^ c))
            System.out.println("Associativity operators \"a ^= b ^ c\" "+ t +" is work!");
        else
            throw new MyException("Associativity operators \"a ^= b ^ c\" not working correctly!");
    }

    public static void associativityTest5(boolean a, boolean b, boolean c) throws MyException {
        boolean t;
        if((t = boolXorWithAssignment(a, boolXorWithAssignment(b, c))) == (a ^= b ^= c))
            System.out.println("Associativity operators \"a ^= b ^= c\" "+ t +" is work!");
        else
            throw new MyException("Associativity operators \"a ^= b ^= c\" not working correctly!");
    }

    //-----//

    public static void addTest(int a, int b) throws MyException {
        int t;
        if((t = add(a, b)) == (a + b))
            System.out.println("Method \"add\" "+ t +" is work!");
        else
            throw new MyException("Method \"add\" not working correctly!");
    }

    public static void modTest(int a, int b) throws MyException {
        int t;
        if((t = mod(a, b)) == (a % b))
            System.out.println("Method \"mod\" "+ t +" is work!");
        else
            throw new MyException("Method \"mod\" not working correctly!");
    }

    public static void assignmentWithSubtractionTest(int a, int b) throws MyException {
        int t;
        if((t = assignmentWithSubtraction(a,b)) == (a -= b))
            System.out.println("Method \"assignmentWithSubtraction\" "+ t +" is work!");
        else
            throw new MyException("Method \"assignmentWithSubtraction\" "+ t +" is work!");
    }

    public static void orTest(int a, int b) throws MyException {
        int t;
        if((t = or(a, b)) == (a | b))
            System.out.println("Method \"or\" "+ t +" is work!");
        else
            throw new MyException("Method \"or\" not working correctly!");
    }

    public static void xorWithAssignmentTest(int a, int b) throws MyException {
        int t;
        if((t = xorWithAssignment(a, b)) == (a ^= b))
            System.out.println("Method \"xorWithAssignment\" "+ t +" is work!");
        else
            throw new MyException("Method \"xorWithAssignment\" not working correctly!");
    }

    public static void moreTest(int a, int b) throws MyException {
        boolean t;
        if((t = more(a, b)) == (a > b))
            System.out.println("Method \"more\" "+ t +" is work!");
        else
            throw new MyException("Method \"more\" not working correctly!");
    }

    public static void boolXorTest(boolean a, boolean b) throws MyException {
        boolean t;
        if((t = boolXor(a, b)) == (a ^ b))
            System.out.println("Method \"boolXor\" "+ t +" is work!");
        else
            throw new MyException("Method \"boolXor\" not working correctly!");
    }

    public static void boolNotTest(boolean a) throws MyException {
        boolean t;
        if((t = boolNot(a)) == (!a))
            System.out.println("Method \"boolNot\" "+ t +" is work!");
        else
            throw new MyException("Method \"boolNot\" not working correctly!");
    }

    public static void boolXorWithAssignmentTest(boolean a, boolean b) throws MyException {
        boolean t;
        if((t = boolXorWithAssignment(a, b)) == (a ^= b))
            System.out.println("Method \"boolXorWithAssignment\" "+ t +" is work!");
        else
            throw new MyException("Method \"boolXorWithAssignment\" not working correctly!");
    }

    public static void boolXorWithAssignmentTest2(boolean a, boolean b) throws MyException {
        boolean t;
        if((t = boolXorWithAssignment(b, a)) == (b ^= a))
            System.out.println("Method \"boolXorWithAssignment\" "+ t +" is work!");
        else
            throw new MyException("Method \"boolXorWithAssignment\" not working correctly!");
    }
}

class MyException extends Exception {
    public String message;
    public MyException(String message)
    {
        this.message = message;
    }
}