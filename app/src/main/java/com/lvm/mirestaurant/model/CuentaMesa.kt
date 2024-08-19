package com.lvm.mirestaurant.model

//TODO
class CuentaMesa(val mesa: Int) {
    private val items: MutableList<ItemMesa> = mutableListOf()
    var aceptaPropina: Boolean = true

    //TODO
    fun agregarItem(itemMenu: ItemMenu, cantidad: Int) {
        val itemMesa = ItemMesa(itemMenu, cantidad)
        items.add(itemMesa)
    }

    //TODO
    fun agregarItem(itemMesa: ItemMesa) {
        items.add(itemMesa)
    }

    fun calcularTotalSinPropina(): Int {
        return items.sumOf { it.calcularSubtotal() }
    }

    fun calcularPropina(): Int {
        return if (aceptaPropina) (calcularTotalSinPropina() * 0.1).toInt() else 0
    }

    //TODO
    fun calcularTotalConPropina(): Int {
        return calcularTotalSinPropina() + calcularPropina()
    }
}