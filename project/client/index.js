const input_border_clr = '#333'
$('#updateform').hide()
// validator - add form
function validateAddFields(){

  $('#warningbox').text('')
  $('#carid').css('border','3px solid'+input_border_clr)
  $('#carmodel').css('border','3px solid'+input_border_clr)
  $('#carcolor').css('border','3px solid'+input_border_clr)
  $('#carprice').css('border','3px solid'+input_border_clr)

  let flag= false, idflag=true, modelflag=true,colorflag=true,priceflag=true
  const input_values = {
    carid : $('#carid').val(),
    carmodel : $('#carmodel').val(),
    carcolor : $('#carcolor').val(),
    carprice : $('#carprice').val()
  }
  if(input_values.carid == ''){
    $('#warningbox').append('Car id cannot be empty. ')
    $('#carid').css('border','3px solid red')
    idflag = false
  }
  if(input_values.carmodel == ''){
    $('#warningbox').append('Car model cannot be empty. ')
    $('#carmodel').css('border','3px solid red')
    modelflag = false
  }
  if(input_values.carcolor == ''){
    $('#warningbox').append('Car color cannot be empty. ')
    $('#carcolor').css('border','3px solid red')
    colorflag = false
  }
  if(input_values.carprice == ''){
    $('#warningbox').append('Car price cannot be empty. ')
    $('#carprice').css('border','3px solid red')
    priceflag = false
  }
  flag = idflag && modelflag && colorflag && priceflag
  return {values:input_values,flag}
} 

function validateupdateFields(){

  $('#warningbox').text('')
  // $('#updateid').css('border','3px solid'+input_border_clr)
  $('#updatemodel').css('border','3px solid'+input_border_clr)
  $('#updatecolor').css('border','3px solid'+input_border_clr)
  $('#updateprice').css('border','3px solid'+input_border_clr)

  let flag= false, idflag=true, modelflag=true,colorflag=true,priceflag=true
  const input_values = {
    carid : $('#updateid').val(),
    carmodel : $('#updatemodel').val(),
    carcolor : $('#updatecolor').val(),
    carprice : $('#updateprice').val()
  }
  if(input_values.carmodel == ''){
    $('#warningbox').append('Car model cannot be empty. ')
    $('#updatemodel').css('border','3px solid red')
    modelflag = false
  }
  if(input_values.carcolor == ''){
    $('#warningbox').append('Car color cannot be empty. ')
    $('#updatecolor').css('border','3px solid red')
    colorflag = false
  }
  if(input_values.carprice == ''){
    $('#warningbox').append('Update Car price cannot be empty. ')
    $('#updateprice').css('border','3px solid red')
    priceflag = false
  }
  flag = idflag && modelflag && colorflag && priceflag
  return {values:input_values,flag}
} 

// fetch data ajax
function loadData(){
  $('#data').css('border','2px solid red')
  setTimeout(()=>{
    $('#data').css('border','2px solid transparent')
  },500)
  $.ajax({
    url:'/cars',
    method: 'GET',
    dataType: 'json',
    success: data=>{
      const carlist = $('#data')
      if(data.length>0)
        carlist.html('<tr><th>Id</th><th>Model</th><th>Color</th><th>Price</th><th>Action</th></tr>')
      else
        carlist.html('')
      data = data.sort((a,b)=>a.id-b.id)
      for(let i=0;i<data.length;i++){
        const caritem = `<tr><td>${data[i].id}</td><td>${data[i].model}</td><td>${data[i].color}</td><td>${data[i].price}</td><td><button class="editbtn" data-id="${data[i].id}" data-model="${data[i].model}" data-color="${data[i].color}" data-price="${data[i].price}">Edit</button><button class="deletebtn" data-id="${data[i].id}" data-model="${data[i].model}" data-color="${data[i].color}" data-price="${data[i].price}">Delete</button></td></tr>`
        carlist.append(caritem)
      }
    },
    error:err=> console.log(err)
  })
}

// get data
$("#getcars").on('click',loadData)
// add data
$("#addform").on('submit',function(e){
  e.preventDefault()

  const val_res = validateAddFields()
  const values = val_res.values
  if(val_res.flag){
    car={id:parseInt(values.carid),model:values.carmodel,color:values.carcolor,price:parseFloat(values.carprice)}
    $.ajax({
      url:'/cars',
      method: 'POST',
      data:car,
      dataType: 'json',
      success:()=> loadData(),
      error:err=> console.log(err)
    })
  }
})

$(document).on('click', '.editbtn', function(){
  let id = $(this).data('id');
  let model = $(this).data('model');
  let color = $(this).data('color');
  let price = $(this).data('price');
  $('#updateform').show()
  $('#updateid').val(id)
  $('#updatemodel').val(model)
  $('#updatecolor').val(color)
  $('#updateprice').val(price)
  $('#updateform').on('submit',function(e){
    e.preventDefault()
    const values={
      updateid : $('#updateid').val(),
      updatemodel : $('#updatemodel').val(),
      updatecolor : $('#updatecolor').val(),
      updateprice : $('#updateprice').val()
    }
    const val_res = validateupdateFields()
    if(val_res.flag){
      $.ajax({
        url:'/cars/modify',
        method: 'POST',
        data:{action:'edit',id:id, model:values.updatemodel, color:values.updatecolor, price:values.updateprice},
        dataType: 'json',
        success:()=>{
          $('#updateform').hide()
          loadData()
        },
        error:err=> console.log(err)
      })
    }
  })
})
$(document).on('click', '.deletebtn', function(){
  var id = $(this).data('id');
  $.ajax({
    url:'/cars/modify',
    method: 'POST',
    data:{action:'delete',id:id},
    dataType: 'json',
    success:()=>loadData(),
    error:err=> console.log(err)
  })
})

$(document).ready(loadData)
