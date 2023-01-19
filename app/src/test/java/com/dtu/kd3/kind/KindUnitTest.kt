package com.dtu.kd3.kind

import com.dtu.kd3.kind.model.UserViewModel
import com.dtu.kd3.kind.model.charities.ThemeManager
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

/**
 * author s205430 - Muaz Ahmed
 */
class KindUnitTest {
    private lateinit var userViewModel: UserViewModel

    @Before
    fun setup() {
        userViewModel = UserViewModel()
    }

    @Test
    fun setName() {
        userViewModel.setName("John")
        assertEquals("John", userViewModel.name.value)
    }

    @Test
    fun testSetDonator() {
        userViewModel.setDonator(true)
        assertTrue(userViewModel.donator.value)
    }

    @Test
    fun donate() {
        userViewModel.setDonation(100.0)
        assertEquals(100.0, userViewModel.donated.value, 0.0)
    }

    @Test
    fun portfolioAdd() {
        val theme = ThemeManager.instance.theme[0]
        userViewModel.addTheme(theme = theme)
        assertTrue(userViewModel.subscribed.size == 1)
    }

    @Test
    fun portfolioRemove() {
        val theme = ThemeManager.instance.theme[0]
        userViewModel.addTheme(theme)
        assertTrue(userViewModel.subscribed.contains(theme))
        userViewModel.removeTheme(theme)
        assertFalse(userViewModel.subscribed.contains(theme))
    }

    @Test
    fun setPaymentMethod() {
        userViewModel.setPaymentMethod("Credit card")
        assertEquals("Credit card", userViewModel.paymentmethod.value)
    }

    @Test
    fun setPercentage() {
        val category = ThemeManager.instance.theme[0].getCategory()
        userViewModel.setPercentage(category, 60)
        assertEquals(60, userViewModel.getPercentage(category))
    }

    @Test
    fun getPercentage() {
        val category = ThemeManager.instance.theme[0].getCategory()
        userViewModel.setPercentage(category, 60)
        assertEquals(60, userViewModel.getPercentage(category))
        userViewModel.setPercentage(category, 0)
        assertEquals(0, userViewModel.getPercentage(category))
    }
}