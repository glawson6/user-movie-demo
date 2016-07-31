def uuid = {
    def uuid = java.util.UUID.randomUUID().toString()
    println "${uuid}"
}
50.times(uuid)
