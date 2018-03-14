
$(function(){
    
    $("[href]").each(function() {
    if (this.href === window.location.href) {
        $(this).addClass("active");
        }
    });
   
    //Accordion
    $("#latest_news").accordion({header:"h3"});
    
    //Image Rotator
    $(".rslides").responsiveSlides();
    
    function rotateImages(){
        var oCurPhoto = $('#photoShow div.current');
        var oNxtPhoto = oCurPhoto.next();
            if(oNxtPhoto.length===0){
                oNxtPhoto = $('#photoShow div:first');
                oCurPhoto.removeClass('current').addClass('previous');
                oNxtPhoto.css({opacity: 0.0}).addClass('current').animate({opacity:1.0}, 2000,function(){oCurPhoto.removeClass('previous');});
            }
        } 
   
    });