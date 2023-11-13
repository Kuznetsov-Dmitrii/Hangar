let state=document.getElementsByClassName('state');
let deleteButton=document.getElementsByClassName('deleteButton');

for(let i=0;i<state.length;i++){
    if(state[i].textContent=='true'){
        state[i].textContent='Активен';
    }else{
        state[i].textContent='Завершен';
        deleteButton[i].hidden = true;
    }
}

