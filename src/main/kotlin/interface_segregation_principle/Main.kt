package interface_segregation_principle

fun main() {

}

/** BIRDS EXAMPLE */

/** Correct implementation
 *
 *  The Interface Segregation Principle advocated for the creation of narrow,
 *  specific interfaces. As we can see below, that is what's going on. A
 *  wrong implementation would force all inheritors to implement drive, sail
 *  and fly functions, which would, in most cases, be an unwanted side effect,
 *  instead of a useful feature.
 *
 *  In case we still want all 3 functionalities, we can inherit from the Vehicle
 *  interface.
 */

interface DrivableVehicle {
    fun drive()
}

interface SailableVehicle {
    fun sail()
}

interface FlyableVehicle {
    fun fly()
}

interface Vehicle : DrivableVehicle, SailableVehicle, FlyableVehicle


/** Incorrect implementation */

interface VehicleIncorrect {
    fun drive()
    fun sail()
    fun fly()
}