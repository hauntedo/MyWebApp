document.addEventListener('DOMContentLoaded', function(){
    var item = localStorage.getItem('state');
    var select = document.getElementById("state");
    select.value = item;
});
function submitForm(){
    var select = document.getElementById("state");
    var value = select.options[select.selectedIndex].value;
    localStorage.setItem('state', value);
}