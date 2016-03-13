// JavaScript Document

$(document).ready(function(){
    $('#formcontact .form-control, #formcontactfooter .form-control').change(function(){
            CheckField($(this));
        });
        
    $('#formcontactfooter').submit(function(e){
        e.preventDefault(e);
    });
    $('#formcontact').submit(function(e){
        e.preventDefault(e);
    });
});

function SubmitFormContact(id)
{
	var result_form = CheckFormFields(id);
        var result;
        
        var situation = $('#situation').val() != undefined ? $('#situation').val() : $('#pro_typ_id').val();
        var message = $('#message').val() != undefined ? $('#message').val() : $('#footer_message').val();
        
        if(result_form){
            $.ajax(
                    {	url:'/ajax/contact.ajax.php',
                            type:'post', 
                            data:{gen : $('#gen_id').val(), lst_name : $('#lst_name').val(), fst_name : $('#fst_name').val(),
                                  ema : $('#'+id+' #ema').val(), situation : situation, company : $('#company').val(), message : message},
                            dataType:'json',
                            async:false,
                            success :function (data){result=data;}
                    }
            );
        
            if (result.success)
            {	
                $('.errormessage').html("");
                $('#'+id+' .form-control').attr("disabled","disabled");
                $('#'+id+' .box-button').css('width','100%').css('text-align','left');
                $('#'+id+' .box-button').html('<div class="message success">' + result.message + '</div>');
            }
            else{
                $('.errormessage').html(result.message);
            }
        }
        
}

function UpdateForm()
{	if($('#situation').val()!=$('#situation option:first').val()){
            $('.container-company-field').removeClass('hide');
        }
        else{
            $('.container-company-field').addClass('hide');
        }
	
}