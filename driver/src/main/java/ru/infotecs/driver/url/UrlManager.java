package ru.infotecs.driver.url;

/**
 * Utility class to manage URLs used for interacting with the backend API.
 * <p>
 * This class contains static constants for various endpoints used
 * by the client to perform operations on the backend service. The URLs
 * include endpoints for setting values, getting values, deleting values,
 * dumping data, and loading data.
 * </p>
 */
public class UrlManager {
    /**
     * URL for the endpoint to set a value in the backend.
     * <p>
     * This URL is used to send POST requests to the backend service
     * to store a new key-value pair or update an existing one.
     * </p>
     */
    public static final String SET_URL = "http://localhost:8080/process/set";

    /**
     * URL for the endpoint to get a value from the backend by key.
     * <p>
     * This URL is used to send GET requests to the backend service
     * to retrieve the value associated with a specific key.
     * </p>
     */
    public static final String GET_URL = "http://localhost:8080/process/get/";

    /**
     * URL for the endpoint to delete a value from the backend by key.
     * <p>
     * This URL is used to send DELETE requests to the backend service
     * to remove the value associated with a specific key.
     * </p>
     */
    public static final String DELETE_URL = "http://localhost:8080/process/delete/";

    /**
     * URL for the endpoint to dump the current state of the backend.
     * <p>
     * This URL is used to send GET requests to the backend service
     * to obtain a file containing the current state or data dump.
     * </p>
     */
    public static final String DUMP_URL = "http://localhost:8080/process/dump";

    /**
     * URL for the endpoint to load data into the backend.
     * <p>
     * This URL is used to send POST requests to the backend service
     * with a file to load data into the backend.
     * </p>
     */
    public static final String LOAD_URL = "http://localhost:8080/process/load";
}
