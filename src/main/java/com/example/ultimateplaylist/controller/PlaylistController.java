package com.example.ultimateplaylist.controller;

import com.example.ultimateplaylist.model.Music;
import com.example.ultimateplaylist.model.Playlist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;


@RestController
@RequestMapping(path = "/api")

public class PlaylistController {

    private playlistService playlistService;
    private static final Logger LOGGER = Logger.getLogger(PlaylistController.class.getName());

//    @Autowired
//    public void setPlaylistService(PlaylistService playlistService) {
//        this.playlistService = playlistService;
//    }

    // http://localhost:9092/api/playlists
    @GetMapping("/playlists")
    public List<Playlist> getPlaylists() {
        LOGGER.info("calling getPlaylists method from controller");

        return playlistService.getPlaylists();
    }

    // http://localhost:9092/api/playlists/1
    @GetMapping(path = "/playlists/{playlistId}")
    public Playlist getPlaylist(@PathVariable Long playlistId) {
        LOGGER.info("calling getPlaylist method from controller");
        return playlistService.getPlaylist(playlistId);
    }

    // http://localhost:9092/api/playlists
    @PostMapping(path = "/playlists")
    public Playlist createCategory(@RequestBody Playlist playlistObject) {
        LOGGER.info("calling createPlaylist method from controller");
        return playlistService.createPlaylist(playlistObject);
    }

    // http://localhost:9092/api/playlists/1
    @PutMapping(path = "/playlists/{playlistId}")
    public Playlist updatePlaylist(@PathVariable(value = "PlaylistId") Long playlistId, @RequestBody Playlist playlistObject) {
        LOGGER.info("calling updatePlaylist method from controller");
        return categoryService.updateCategory(categoryId, playlistObject);
    }

    // http://localhost:9092/api/categories/1
    @DeleteMapping("/categories/{categoryId}")
    public Category deleteCategory(@PathVariable(value = "categoryId") Long categoryId) {
        LOGGER.info("calling deleteCategory method from controller");
        return categoryService.deleteCategory(categoryId);
    }

    // http://localhost:9092/api/categories/1/recipes
    @PostMapping("/categories/{categoryId}/recipes")
    public Recipe createCategoryRecipe(
            @PathVariable(value = "categoryId") Long categoryId,
            @RequestBody Recipe recipeObject) {
        LOGGER.info("calling createCategoryRecipe method from controller");
        return categoryService.createCategoryRecipe(categoryId, recipeObject);
    }

    // http://localhost:9092/api/categories/1/recipes/1
    @GetMapping(path = "/categories/{categoryId}/recipes/{recipeId}")
    public Recipe getCategoryRecipe( @PathVariable(value = "categoryId") Long categoryId,
                                     @PathVariable(value = "recipeId") Long recipeId){
        LOGGER.info("calling getCategoryRecipe method from controller");
        return categoryService.getCategoryRecipe(categoryId, recipeId);
    }

    // http://localhost:9092/api/categories/1/recipes
    @GetMapping(path = "/categories/{categoryId}/recipes")
    public List<Recipe> getCategoryRecipes( @PathVariable (value = "categoryId") Long categoryId){
        LOGGER.info("calling getCategoryRecipes method from controller");
        return categoryService.getCategoryRecipes(categoryId);
    }

    // http://localhost:9092/api/categories/1/recipes/1
    @PutMapping(path= "/categories/{categoryId}/recipes/{recipeId}" )
    public Recipe updateCategoryRecipe( @PathVariable (value = "categoryId") Long categoryId,
                                        @PathVariable(value = "recipeId") Long recipeId,
                                        @RequestBody Recipe recipeObject){
        return categoryService.updateCategoryRecipe(categoryId,recipeId,recipeObject);
    }

    // http://localhost:9092/api/categories/1/recipes/1
    @DeleteMapping(path = "/categories/{categoryId}/recipes/{recipeId}")
    public Recipe deleteCategoryRecipe(@PathVariable (value = "categoryId") Long categoryId,
                                       @PathVariable (value = "recipeId") Long recipeId){
        return categoryService.deleteCategoryRecipe(categoryId,recipeId);
    }

}
