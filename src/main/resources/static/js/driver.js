var state=document.getElementsByClassName('state');

for(var i=0;i<state.length;i++){
    if(state[i].text==='true'){
        state[i].text="Свободен";
    }else{
        state[i].text="Занят";
    }
}
