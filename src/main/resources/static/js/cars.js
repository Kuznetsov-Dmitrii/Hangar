var state=document.getElementsByClassName('state');


function checkForm(el){
    var name = el.name.value;
    var number = el.number.value;
    var loadCapacity = el.loadCapacity.value;
    var hangarNumber = el.hangarNumber.value;
    if(name.length > 3 && name.length < 32){
        if(number.length > 7 && number.length < 12){
            if(loadCapacity > 10)
            {
                if(hangarNumber>0){
                    return true;
                }else{
                  alert("Ошибка в номере ангара");
                }
            }else{
                alert("Ошибка в грузоподъемности");
            }
        }else{
            alert("Ошибка в номере");
        }
    }else{
        alert("Введите назваие машины");
    }
    return false;
}

for(var i=0;i<state.length;i++){
    if(state[i].text==='true'){
        state[i].text="Свободна";
    }else{
        state[i].text="Занятя";
    }
}
