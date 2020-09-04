class Solution
{
    public void sortColors(int[] nums)
    {
        int zero=0; int two=nums.length-1;
        int i=0;

        while(i<=two)
        {
            if(nums[i]==0){
                swap(nums, i, zero);
                zero++;
                i++;
            }else if(nums[i]==2){
                swap(nums, i, two);
                two--;
            }else
                i++;
        }
    }

    static void swap(int[] nums, int a, int b)
    {
        if(a==b) return;
        nums[a]= nums[a]^nums[b];
        nums[b]= nums[a]^nums[b];
        nums[a]= nums[a]^nums[b];
    }
}