const express = require('express')
const app = express()
const path = require('path')
const port = process.env.PORT || 3000
var bodyParser = require('body-parser')
app.use(bodyParser.urlencoded({ extended: false }))

function Car(id,model,color,price){
  this.id = id
  this.model = model
  this.color = color
  this.price = price
}

let cars = [new Car(152,"Honda","Grey",20),new Car(155,"Mercedes","Red",40),new Car(153,"Jaguar","Black",50),new Car(159,"Honda","White",25),new Car(151,"AM","Grey",52)]

const staticpath = path.join(__dirname, '../client')
app.use(express.static(staticpath))

// homepage
app.get('/',(req,res)=>{
  res.sendFile(path.join(__dirname,'client\\index.html'))
})
// view
app.get('/cars',(req,res)=>{
  res.send(cars)
})
// add
app.post('/cars/add',(req,res)=>{
  let obj = req.body
  let c = new Car(parseInt(obj.id),obj.model,obj.color,parseInt(obj.price))
  if(!cars.some(car=>car.id == c.id))
    cars.push(c)
  res.send(cars)
})
// modify
app.post('/cars/modify',(req,res)=>{
  let {action,id,model,color,price} = req.body
  if(action==='edit'){
    let i = cars.findIndex((car)=>car.id === parseInt(id))
    cars[i].model = model
    cars[i].color = color
    cars[i].price = price
  }
  else if(action==='delete'){
    let i = cars.findIndex((car)=>car.id == id)
    if(i!=-1){
      cars.splice(i,1)
    }
  }
  else res.send(null)
  res.send(cars)
})

app.listen(port,()=>console.log(`Listening on port ${port}`))