package mokito;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

class AddTest {
    @InjectMocks
    private Add add;
    @Mock
    private ValidNumber validNumber;

    @Mock
    private Print print;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void addTest() {
        when(validNumber.check(3)).thenReturn(true);
        boolean checkNumber = validNumber.check(3);
        assertEquals(true,checkNumber);

        when(validNumber.check("a")).thenReturn(false);
        checkNumber = validNumber.check("a");
        assertEquals(false, checkNumber);
    }

    @Test
    void addMockException() {
        Exception exception = null;
        when(validNumber.checkZero(0)).thenThrow(new ArithmeticException("Debe ingresar un valor mayor a cero"));
        try {
            validNumber.checkZero(0);
        }catch (ArithmeticException e){
            exception = e;
        }
        assertNotNull(exception);
    }

    @Test
    void addRealMethod(){
        when(validNumber.check("3")).thenCallRealMethod();
        assertEquals(true, validNumber.check("3"));
    }

    @Test
    void addDoubleToIntTheAnswerTest() {
        Answer<Integer> answers = new Answer<Integer>() {
            @Override
            public Integer answer(InvocationOnMock invocationOnMock) throws Throwable {
                return 7;
            }
        };
        when(validNumber.doubleToInt(7.7)).thenAnswer(answers);
        assertEquals(14, add.addInt(7.7));
    }

     /*******************************
     /* Patrones de pruebas
     *********************************/
    @Test
    void patterBDDTest() {
        //Arrange
        when(validNumber.check(4)).thenReturn(true);
        when(validNumber.check(5)).thenReturn(true);
        //Act
        int result = add.add(4,5);
        //Assert
        assertEquals(9, result);

    }
    @Test
    void patterTest() {
        //Given
        given(validNumber.check(4)).willReturn(true);
        given(validNumber.check(5)).willReturn(true);
        //When
        int result = add.add(4,5);
        //Them
        assertEquals(9, result);

    }
    @Test
    void argumentMatcherTest() {
        given(validNumber.check(anyInt())).willReturn(true);
        int result = add.add(15,5);
        assertEquals(20, result);
    }

    @Test
    void addPrintTest() {
        //given
        given(validNumber.check(4)).willReturn(true);
        given(validNumber.check(5)).willReturn(true);
        //When
        add.addPrint(4,5);
        //Then
        verify(validNumber).check(4);
        //verify(validNumber, times(2)).check(4);
        //verify(validNumber, times(2)).check(anyInt());
        verify(validNumber, never()).check(99);
        verify(validNumber, atLeast(1)).check(4);
        verify(validNumber, atMost(3)).check(4);

        verify(print).showMessage(9);
        verify(print, never()).showError();
    }
}