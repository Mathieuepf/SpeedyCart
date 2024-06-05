package fr.epf.min1.speedycart.data

fun Shop.getCompleteAddress() = "${this.address.number} ${this.address.road} ${this.address.city}"