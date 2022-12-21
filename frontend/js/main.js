window.onscroll = function() {
    if (window.pageYOffset > 10) {
        document.getElementById('header').style = "position: fixed; width: 100%;"
    }else if(window.pageYOffset < 2){
        document.getElementById('header').style = "position: relative; width: 100%;"
    }
    if(window.pageYOffset > document.getElementById("product1").offsetTop-700){
        document.getElementById('shopNav').classList.add('active')
        document.getElementById('homeNav').classList.remove('active')
    }else{
        document.getElementById('shopNav').classList.remove('active')
        document.getElementById('homeNav').classList.add('active')
    }
}

function scrollToShop(){
    window.scrollTo(0, document.getElementById("product1").offsetTop-300)
}
function scrollToHome(){
    window.scrollTo(0, 0)
}