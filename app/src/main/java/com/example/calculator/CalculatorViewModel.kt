package com.example.calculator

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class CalculatorViewModel:ViewModel() {
    var state by mutableStateOf(CalculatorState())
    fun onAction(action: CalculatorAction){
        when(action){
            is CalculatorAction.Clear->state= CalculatorState()
            is CalculatorAction.Delete->delete()
            is CalculatorAction.Decimal->decimal()
            is CalculatorAction.Calculate->calculate()
            is CalculatorAction.Number->enternum(action.number)
            is CalculatorAction.Operation->enterOp(action.operation)

        }
    }
    private fun delete(){
        when{
            state.number2.isNotBlank()->state=state.copy(number2=state.number2.dropLast(1))
            state.operation!=null->state=state.copy(operation = null)
            state.number1.isNotBlank()->state=state.copy(number1=state.number1.dropLast(1))
        }

    }
    private fun decimal(){
if(state.operation==null && state.number1.isNotBlank()&& !state.number1.contains(".")){
    state=state.copy(number1=state.number1+'.')
    return
}
        if(state.number2.isNotBlank()&& !state.number2.contains(".")){
            state=state.copy(number2=state.number2+'.')
            return
        }
    }

    private fun calculate(){
        var number1=state.number1.toDoubleOrNull()
        var number2=state.number2.toDoubleOrNull()
        if(number1!=null && number2!=null ){
            val result=when(state.operation){
                is CalculatorOperation.Add->number2+number1
                is CalculatorOperation.Subtract->number1-number2
                is CalculatorOperation.Multiply->number1*number2
                is CalculatorOperation.Divide->number1/number2
                null->return
            }
            state=state.copy(number1=result.toString().take(15))
            state.number2=""
            state.operation=null
        }

    }

    private fun enternum( number:Int){
if(state.operation==null){
    if(state.number1.length>=8){
        return
    }
    state=state.copy(number1=state.number1+number)
    return
}
        if(state.number2.length>=8){
            return
        }
        state=state.copy(number2=state.number2+number)
        return
    }

    private fun enterOp(operation: CalculatorOperation){
if(state.number1.isNotBlank()){
state=state.copy(operation=operation)
}
    }

}



