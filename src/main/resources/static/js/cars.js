let state=document.getElementsByClassName('state');
const table = document.querySelector('table');

for(let i=0;i<state.length;i++){
    if(state[i].textContent=='true'){
        state[i].textContent='Свободна';
    }else{
        state[i].textContent='Занята';
    }
}

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


let colIndex = -1;
const sortTable = function (index, type, isSorted) {
    const tbody = table.querySelector('tbody');

    const compare = function (rowA, rowB) {
    const rowDataA = rowA.cells[index].innerHTML;
    const rowDataB= rowB.cells[index].innerHTML;

    switch (type) {
        case 'integer': case 'double':
            return rowDataA - rowDataB;
            break;
        case 'date':
            const dateA = rowDataA.split('.').reverse().join('-');
            const dateB= rowDataB.split('.').reverse().join('-');
            return new Date(dateA).getTime()- new Date(dateB).getTime();
            break;
        case 'text':
            if (rowDataA< rowDataB) return -1;
            else if (rowDataA> rowDataB) return 1; return 0;
            break;
        }
    }
    let rows = [].slice.call(tbody.rows);

    rows.sort(compare);

    if (isSorted) rows.reverse();

    table.removeChild(tbody);

    for (let i = 0; i < rows.length; i++) {
        tbody.appendChild(rows[i]);
    }
    table.appendChild(tbody);
}
table.addEventListener('click', (e) => {
    const el = e.target;
    if (el.nodeName != 'TH') return;

    const index = el.cellIndex;
    const type = el.getAttribute('data-type');

    sortTable(index, type, colIndex == index);
    colIndex = (colIndex == index) ? -1: index;
 });

