jQuery(function(){
    function countDigits(number) {
        var numberString = number.toString();

        if (numberString.length > 15){
            return true;
        }else{
            return false;
        }
    }
    function valid(){
        let x = jQuery("#vvodx").val();
        let y = jQuery("#vvody").val();

        //jQuery("#group").html(x.lenght());
        if(isNaN(x) || x=="" || countDigits(x) || !((x<=3 & x>=-3))){
            vvodx.classList.add('invalid');
        }
        if(isNaN(y) || y=="" || !(y==-5 || y==-4 || y==-3 || y==-2 || y==-1 || y==0 || y==1 || y==2 || y==3)){
            vvody.classList.add('invalid');
        }
        if ((x<=3 & x>=-3) & !(isNaN(x) || x=="" || countDigits(x))){
            vvodx.classList.remove('invalid');
        }
        if ((y==-5 || y==-4 || y==-3 || y==-2 || y==-1 || y==0 || y==1 || y==2 || y==3 ) & !(isNaN(y) || y=="")){
            vvody.classList.remove('invalid');
        }
        if ((x<=3 & x>=-3) & (y==-5 || y==-4 || y==-3 || y==-2 || y==-1 || y==0 || y==1 || y==2 || y==3) & !(isNaN(x) || x=="" || countDigits(x)) & !(isNaN(y) || y=="")){
            return true;}
        return false;

    };
    function keyevent(){
        if(!valid()){
            jQuery("#submit").prop("disabled", true);
        }else{
            jQuery("#submit").prop("disabled", false);
        }

    };


    jQuery(".ybutton").on("click", function(){jQuery("#vvody").val(jQuery(this).html());
        event.preventDefault();
        keyevent();});
    jQuery("#vvodx").on("keypress", keyevent);
    jQuery("#vvody").on("keypress", keyevent);
    jQuery("#vvodx").on("change", keyevent);
    jQuery("#vvody").on("change", keyevent);
    jQuery("#vvodx").on("click", keyevent);
    jQuery("#vvody").on("click", keyevent);
    jQuery("#vvodx").on("hover", keyevent);
    jQuery("#vvody").on("hover", keyevent);
    jQuery("#vvodx").on("keyup", keyevent);
    jQuery("#vvody").on("keyup", keyevent);

    jQuery("#forma").on("submit", function(){
        if(!valid()){
            event.preventDefault();
            keyevent();
        };
    });
    //jQuery(".vvod").on("click", keyevent);

});