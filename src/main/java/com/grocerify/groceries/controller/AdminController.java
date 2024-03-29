package com.grocerify.groceries.controller;

import com.grocerify.groceries.model.GroceryItem;
import com.grocerify.groceries.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/groceryitem")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<GroceryItem> addGroceryItem(@RequestBody GroceryItem groceryItem) {
        GroceryItem addedItem = adminService.addGroceryItem(groceryItem);
        return new ResponseEntity<>(addedItem, HttpStatus.CREATED);
    }

    @GetMapping("/groceryitem")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<GroceryItem>> viewGroceryItems() {
        List<GroceryItem> groceryItems = adminService.viewGroceryItems();
        return new ResponseEntity<>(groceryItems, HttpStatus.OK);
    }

    @DeleteMapping("/groceryitem/{itemId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> removeGroceryItem(@PathVariable Long itemId) {
        adminService.removeGroceryItem(itemId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/groceryitem/{itemId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<GroceryItem> updateGroceryItem(@PathVariable Long itemId, @RequestBody GroceryItem updatedItem) {
        GroceryItem updated = adminService.updateGroceryItem(itemId, updatedItem);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @PatchMapping("/groceryitem/inventory/{itemId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> manageInventory(@PathVariable Long itemId, @RequestParam int quantity) {
        adminService.manageInventory(itemId, quantity);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}