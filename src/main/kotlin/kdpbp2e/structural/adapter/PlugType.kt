package kdpbp2e.structural.adapter

interface PlugTypeF {
    val hasPower: Int // 1이면 접원 공급 중
}

interface PlugTypeA {
    val hasPower: String // "TRUE, or "FALSE"
}

interface UsbMini {
    val hasPower: Power
}

enum class Power {
    TRUE, FALSE
}

interface UsbTypeC {
    val hasPower: Boolean
}

// [목표]
// 전원 값이 F 타입 플러그에서 휴대폰까지 흐르게 하는 것

// 1. 휴대폰은 C 타입
fun cellPhone(chargeCable: UsbTypeC) {
    if (chargeCable.hasPower) {
        println("I've Got The Power!")
    } else {
        println("No Power")
    }
}

// 2. 전원 콘센트는 Plug F 타입
fun powerOutlet(): PlugTypeF {
    return object : PlugTypeF {
        override val hasPower = 1
    }
}

// 3. 충전기는 Plug A 타입
fun charge(plug: PlugTypeA): UsbMini {
    return object : UsbMini {
        override val hasPower = Power.valueOf(plug.hasPower)
    }
}

// 4. F 타입 콘센트에 A 타입 플러그를 사용할 수 있게 확장함수 구현하기
fun PlugTypeF.toPlugTypeA(): PlugTypeA {
    val hasPower = if (this.hasPower == 1) "TRUE" else "FALSE"
    return object : PlugTypeA {
        override val hasPower = hasPower
    }
}

// 5. Mini USB와 USB-C
fun UsbMini.toUsbTypeC(): UsbTypeC {
    val hasPower = this.hasPower == Power.TRUE
    return object : UsbTypeC {
        override val hasPower = hasPower
    }
}

// 4. 폰, 전원, 충전기 연결하기
fun main() {
    cellPhone(
        charge(
            powerOutlet().toPlugTypeA()
        ).toUsbTypeC()
    )
}