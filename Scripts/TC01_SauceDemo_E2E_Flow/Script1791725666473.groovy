import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.ConditionType


TestObject makeObject(String name, String xpath) {
    TestObject to = new TestObject(name)
    to.addProperty("xpath", ConditionType.EQUALS, xpath)
    return to
}

int timeout = 10 // Durasi max tunggu (detik)


WebUI.comment("=== START TEST CASE 1: LOGIN ===")

// 1. Open Browser & Navigate
WebUI.openBrowser('')
WebUI.navigateToUrl('https://www.saucedemo.com/')
WebUI.maximizeWindow()

// 2. Identify Elements
TestObject txtUsername = makeObject("txtUsername", "//input[@id='user-name']")
TestObject txtPassword = makeObject("txtPassword", "//input[@id='password']")
TestObject btnLogin    = makeObject("btnLogin", "//input[@id='login-button']")

// 3. Dynamic Wait & Action
WebUI.waitForElementVisible(txtUsername, timeout)
WebUI.setText(txtUsername, 'standard_user')

WebUI.waitForElementVisible(txtPassword, timeout)
WebUI.setText(txtPassword, 'secret_sauce')

WebUI.waitForElementClickable(btnLogin, timeout)
WebUI.click(btnLogin)

// 4. Assertion Test Case 1
TestObject lblProducts = makeObject("lblProducts", "//span[@class='title' and text()='Products']")
WebUI.waitForElementVisible(lblProducts, timeout)
WebUI.verifyElementPresent(lblProducts, timeout)
println("SUCCESS: Test Case 1 - Login Berhasil!")



WebUI.comment("=== START TEST CASE 2: SORTING ===")

// 1. Identify Dropdown Element
TestObject dropdownSort = makeObject("dropdownSort", "//select[@class='product_sort_container']")

// 2. Dynamic Wait & Select Option
WebUI.waitForElementVisible(dropdownSort, timeout)
// Memilih opsi berdasarkan value 'lohi' (Price: Low to High)
WebUI.selectOptionByValue(dropdownSort, 'lohi', false)

// 3. Assertion Test Case 2
TestObject activeSortOption = makeObject("activeSortOption", "//span[@class='active_option' and text()='Price (low to high)']")
WebUI.waitForElementVisible(activeSortOption, timeout)
WebUI.verifyElementPresent(activeSortOption, timeout)
println("SUCCESS: Test Case 2 - Sorting Price Low to High Berhasil!")



// ----------------------------------------------------------------------------
// TEST CASE 3: ADD TO CART (PERBAIKAN)
// ----------------------------------------------------------------------------
WebUI.comment("=== START TEST CASE 3: ADD TO CART ===")

// 1. Identify Add To Cart Button & Cart Icon
TestObject btnAddToCart = makeObject("btnAddToCart", "//button[@id='add-to-cart-sauce-labs-backpack']")
TestObject iconCart     = makeObject("iconCart", "//a[@class='shopping_cart_link']")

// 2. Click Add To Cart & Go To Cart Page
WebUI.waitForElementClickable(btnAddToCart, timeout)
WebUI.click(btnAddToCart)

WebUI.waitForElementClickable(iconCart, timeout)
WebUI.click(iconCart)

// 3. TAMBAHAN: Pastikan halaman keranjang sudah terbuka dengan mengecek judul "Your Cart"
TestObject lblCartTitle = makeObject("lblCartTitle", "//span[@class='title' and text()='Your Cart']")
WebUI.waitForElementVisible(lblCartTitle, timeout)

// 4. Assertion Test Case 3: Verifikasi produk ada di keranjang
TestObject cartItemName = makeObject("cartItemName", "//div[@class='inventory_item_name' and text()='Sauce Labs Backpack']")
WebUI.waitForElementVisible(cartItemName, timeout)
WebUI.verifyElementPresent(cartItemName, timeout)
println("SUCCESS: Test Case 3 - Item Berhasil Ditambahkan ke Keranjang!")


WebUI.comment("=== START TEST CASE 4: CHECKOUT PROCESS ===")

// 1. Identify Checkout Elements
TestObject btnCheckout   = makeObject("btnCheckout", "//button[@id='checkout']")
TestObject txtFirstName  = makeObject("txtFirstName", "//input[@id='first-name']")
TestObject txtLastName   = makeObject("txtLastName", "//input[@id='last-name']")
TestObject txtPostalCode = makeObject("txtPostalCode", "//input[@id='postal-code']")
TestObject btnContinue   = makeObject("btnContinue", "//input[@id='continue']")
TestObject btnFinish     = makeObject("btnFinish", "//button[@id='finish']")

// 2. Click Checkout
WebUI.waitForElementClickable(btnCheckout, timeout)
WebUI.click(btnCheckout)

// 3. Fill Form Data
WebUI.waitForElementVisible(txtFirstName, timeout)
WebUI.setText(txtFirstName, 'Budi')

WebUI.waitForElementVisible(txtLastName, timeout)
WebUI.setText(txtLastName, 'Santoso')

WebUI.waitForElementVisible(txtPostalCode, timeout)
WebUI.setText(txtPostalCode, '12345')

// 4. Click Continue & Finish
WebUI.waitForElementClickable(btnContinue, timeout)
WebUI.click(btnContinue)

WebUI.waitForElementClickable(btnFinish, timeout)
WebUI.click(btnFinish)

// 5. Assertion Test Case 4
TestObject lblCompleteHeader = makeObject("lblCompleteHeader", "//h2[@class='complete-header' and text()='Thank you for your order!']")
WebUI.waitForElementVisible(lblCompleteHeader, timeout)
WebUI.verifyElementPresent(lblCompleteHeader, timeout)
println("SUCCESS: Test Case 4 - Process Checkout Selesai!")

// Tutup Browser
WebUI.closeBrowser()