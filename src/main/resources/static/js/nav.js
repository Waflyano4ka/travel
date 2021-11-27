const buttons = document.getElementsByClassName('nav-link');
let currentLocation = window.location.pathname;

for (var i = 0; i < buttons.length; i++) {
    console.log(currentLocation);
    console.log(buttons[i].pathname);
    if (currentLocation.includes(buttons[i].pathname)){
        buttons[i].className = ('nav-link bg-warning text-dark');
    }
}