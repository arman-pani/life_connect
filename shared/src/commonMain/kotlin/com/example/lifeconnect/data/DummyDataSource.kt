package com.example.lifeconnect.data

import com.example.lifeconnect.models.BloodRequestDetails

val dummyBloodRequests = listOf(
    BloodRequestDetails(
        bloodImage = R.drawable.a_plus,
        title = "Urgent A+ Blood Needed",
        personName = "Rahul Sharma",
        contact = "+91 9876543210",
        location = "Apollo Hospital, New Delhi",
        time = "Today, 5:30 PM",
        bagsRequired = 2
    ),
    BloodRequestDetails(
        bloodImage = R.drawable.o_minus,
        title = "O- Donor Required",
        personName = "Sneha Verma",
        contact = "+91 9123456789",
        location = "Fortis Hospital, Mumbai",
        time = "Tomorrow, 9:00 AM",
        bagsRequired = 1
    ),
    BloodRequestDetails(
        bloodImage = R.drawable.b_plus,
        title = "Need B+ Blood Urgently",
        personName = "Amit Kumar",
        contact = "+91 9988776655",
        location = "AIIMS, Delhi",
        time = "Today, 3:00 PM",
        bagsRequired = 3
    ),
    BloodRequestDetails(
        bloodImage = R.drawable.ab_minus,
        title = "AB- Blood Required",
        personName = "Priya Singh",
        contact = "+91 9090909090",
        location = "CMC Hospital, Vellore",
        time = "Tomorrow, 11:00 AM",
        bagsRequired = 2
    ),
    BloodRequestDetails(
        bloodImage = R.drawable.a_minus,
        title = "A- Blood Needed for Surgery",
        personName = "Ravi Mehta",
        contact = "+91 9012345678",
        location = "Narayana Hospital, Bengaluru",
        time = "Today, 7:00 PM",
        bagsRequired = 1
    )
)
