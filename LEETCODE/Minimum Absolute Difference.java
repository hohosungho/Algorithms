class Solution {
    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(arr);
        int min = arr[1] - arr[0];
        for(int i = 1; i < arr.length; i++){
            if(min > arr[i]-arr[i-1] ){
                min = arr[i]-arr[i-1];
                list.clear();
                List<Integer> pair = new ArrayList<>();
                pair.add(arr[i-1]);
                pair.add(arr[i]);
                list.add(pair);
            } else if( min == arr[i]-arr[i-1]){
                List<Integer> pair = new ArrayList<>();
                pair.add(arr[i-1]);
                pair.add(arr[i]);
                list.add(pair);
            }
        }
        return list;
    }
}
