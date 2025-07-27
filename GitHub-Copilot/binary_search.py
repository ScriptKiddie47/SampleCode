def binary_search(arr, target):
    """
    Performs binary search on a sorted list to find the index of a target value.

    Args:
        arr (list): A sorted list of elements to search.
        target: The value to search for in the list.

    Returns:
        int: The index of the target value if found; otherwise, -1.
    """
    left, right = 0, len(arr) - 1
    while left <= right:
        mid = (left + right) // 2
        if arr[mid] == target:
            return mid
        elif arr[mid] < target:
            left = mid + 1
        else:
            right = mid - 1
    return -1

# Function to reverse a string
