package cinema

fun main() {
    println("Enter the number of rows:")
    val rows = readln().toInt()
    println("Enter the number of seats in each row:")    
    val seats = readln().toInt()
    val list = MutableList(rows) { MutableList(seats) { "S" } }
    println()

    var value = 5

    while (value != 0) {
        println("1. Show the seats")
        println("2. Buy a ticket")
        println("3. Statistics")
        println("0. Exit")

        value = readln().toInt()
        if (value == 0) {
            break
        } else if (value == 1) {
            ShowSeats(list, rows, seats)
        } else if (value == 2) {
            BuyTicket(list, rows, seats)
        } else if (value == 3) {
            Statistics(list, rows, seats)
        }
    }
}

fun ShowSeats(list: MutableList<MutableList<String>>, rows: Int, seats: Int) {
    println("Cinema:")
    print("  ")
    for (i in 1..seats) {
        if (i == seats) {
            print("$i")
        } else {
            print("$i ")
        }
    }
    println()
    for (j in 1..rows) {
        print("$j ")
        for (i in 1..seats) {
            if (i == seats) {
                print("${list[j-1][i-1]}")
            } else {
                print("${list[j-1][i-1]} ")
            }
        }
        println()
    }

}

fun BuyTicket(list: MutableList<MutableList<String>>, rows: Int, seats: Int) {
    var err = 0
    do {
        println("Enter a row number:")
        val row_sel = readln().toInt()
        println("Enter a seat number in that row:")
        val seat_sel = readln().toInt()
        if ( (row_sel > rows) || (seat_sel > seats) ) {
            err = 0
            println("Wrong input!")
        } else {
            val chosen_seat = list[row_sel-1][seat_sel-1]
            if (chosen_seat == "B") {
                err = 0
                println("That ticket has already been purchased!")
            } else {
                err = 1
                if (rows*seats <= 60) {
                    println("Ticket price: $10")
                } else {
                    if (row_sel <= (rows/2)){
                        println("Ticket price: $10")
                    } else {
                        println("Ticket price: $8")
                    }
                }
                list[row_sel-1].set(seat_sel-1, "B")
            }
        }
        } while(err == 0) 
}

fun Statistics(list: MutableList<MutableList<String>>, rows: Int, seats: Int) {
    print("Number of purchased tickets: ")
    var check: String = " "
    var tickets: Int = 0
    for (i in 1..rows) {
        for (j in 1..seats) {
            check = list[i-1][j-1]
            if (check == "B") {
                tickets += 1
            }
        }
    }
    println(tickets)

    print("Percentage: ")
    val ratio: Float = (tickets.toFloat()/(rows.toFloat()*seats.toFloat()))*100
    val percentage = "%.2f".format(ratio)
    println(percentage+"%")

    var curr_income = 0
    print("Current income: $")
    if (rows*seats <= 60) {
        curr_income = tickets*10
        println(curr_income)
    } else {
        for (i in 1..rows) {
            for (j in 1..seats) {
                check = list[i-1][j-1]
                if (check == "B" && i <= rows/2) {
                    curr_income += 10
                } else if (check == "B" && i > rows/2){
                    curr_income += 8
                }
            }
        }
        println(curr_income)
    }
    
    var income = 0
    print("Total income: $")
    if (rows*seats <= 60){
        println(rows*seats*10)
    } else {
        if (rows%2 == 0) {
            income = ((rows/2)*seats*10)+((rows/2)*seats*8)
        } else {
            income = ((rows/2)*seats*10)+(((rows/2)+1)*seats*8)
        }
        println(income)
    } 
}
